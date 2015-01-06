using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq.Expressions;
using System.Reflection;

namespace CPT.ViewModels
{
    /// <summary>
    /// BaseModel is class for application data model classes to inherit from,
    /// It provides a simple implementation of INotifyPropertyChanged and IDataErrorInfo which reduces coding redundancy.
    /// </summary>
    public abstract class BaseModel : INotifyPropertyChanged, IDataErrorInfo
    {
        /// <summary>
        /// Implemented from INotifyPropertyChanged.
        /// </summary>
        public event PropertyChangedEventHandler PropertyChanged;

        /// <summary>
        /// Implemented from IDataErrorInfo.
        /// </summary>
        /// <param name="propertyName">The property name to retrieve error information about.</param>
        /// <returns>The error message for the specified property name.</returns>
        public virtual string this[string propertyName]
        {
            get { return GetPropertyError(propertyName); }
        }

        /// <summary>
        /// Implemented from IDataErrorInfo.
        /// Provides an accumulated error message for the model.
        /// </summary>
        public virtual string Error
        {
            get
            { 
                string error = "";
                foreach (PropertyInfo propInfo in GetType().GetProperties())
                {
                    string errorMessage = this[propInfo.Name];
                    if (!String.IsNullOrEmpty(errorMessage))
                        error += String.Format("{0} ", errorMessage);
                }
                return error;
            }
        }

        /// <summary>
        /// Indicates whether the model is currently in a valid state or not.
        /// </summary>
        public virtual bool IsValid
        {
            get { return String.IsNullOrEmpty(Error); }
        }

        /// <summary>
        /// A dictionary of model properties and their values.
        /// </summary>
        private Dictionary<string, object> InternalValues = new Dictionary<string, object>();

        /// <summary>
        /// A dictionary of model property validation rules and their error messages.
        /// </summary>
        private Dictionary<string, Dictionary<Func<bool>, string>> ComplexValidation = new Dictionary<string, Dictionary<Func<bool>, string>>();

        /// <summary>
        /// BaseModel protected constructor.
        /// </summary>
        protected BaseModel()
        {
        }

        /// <summary>
        /// Nulls all model properties.
        /// </summary>
        protected void ClearProperties()
        {
            InternalValues.Clear();
        }

        /// <summary>
        /// Retrieves the property name specified by the given expression.
        /// </summary>
        /// <typeparam name="T">The data type of the specified property.</typeparam>
        /// <param name="propSelector">A member expression for the specified property.</param>
        /// <returns>The specified properties property name as a string.</returns>
        protected string GetPropertyName<T>(Expression<Func<T>> propSelector)
        {
            return GetPropertyName((LambdaExpression)propSelector);
        }

        /// <summary>
        /// Retrieves the property name specified by the given expression.
        /// </summary>
        /// <param name="expression">A LambdaExpression specifying the property.</param>
        /// <returns>The specified properties property name as a string.</returns>
        protected string GetPropertyName(LambdaExpression expression)
        {
            MemberExpression memberExpression = expression.Body as MemberExpression;
            if (memberExpression == null)
                throw new InvalidOperationException("No property specified by the given expression.");
            return memberExpression.Member.Name;
        }

        /// <summary>
        /// Retrieves all error messages for the specified property as a joined string.
        /// Check's against the following:
        /// * Complex Validation Rules (AddComplexRule())
        /// * ValidationAttribute's
        /// * MetadataTypeAttribute -> ValidationAttribute's
        /// </summary>
        /// <param name="propName">The specified property.</param>
        /// <returns>A joined string of error messages.</returns>
        private string GetPropertyError(string propName)
        {
            string errors = String.Empty;
            if (ComplexValidation.ContainsKey(propName))
                foreach (KeyValuePair<Func<bool>, string> rule in ComplexValidation[propName])
                    if (rule.Key())
                        errors += String.Format("{0} ", rule.Value);
            return errors;
        }

        /// <summary>
        /// Adds a complex rule to the internal property rules collection.
        /// </summary>
        /// <typeparam name="T">The target properties type.</typeparam>
        /// <param name="propSelector">The member expression for the target property.</param>
        /// <param name="isError">The function specifying whether the rule results in a error or not.</param>
        /// <param name="errorMessage">The error message which is used when the rule results in an error state.</param>
        protected void AddComplexRule<T>(Expression<Func<T>> propSelector, Func<bool> isError, string errorMessage)
        {
            AddComplexRule(GetPropertyName(propSelector), isError, errorMessage);
        }

        /// <summary>
        /// Adds a complex rule to the internal property rules collection.
        /// </summary>
        /// <param name="propName">The property name of the target property.</param>
        /// <param name="isError">The function specifying whether the rule results in a error or not.</param>
        /// <param name="errorMessage">The error message which is used when the rule results in an error state.</param>
        private void AddComplexRule(string propName, Func<bool> isError, string errorMessage)
        {
            if (!ComplexValidation.ContainsKey(propName))
                ComplexValidation.Add(propName, new Dictionary<Func<bool>, string>());
            ComplexValidation[propName].Add(isError, errorMessage);
        }

        /// <summary>
        /// Gets the value of the specified property.
        /// </summary>
        /// <typeparam name="T">The type of the specified property.</typeparam>
        /// <param name="propSelector">The member expression specifying the property to retrieve's name.</param>
        /// <returns>The value of the specified property.</returns>
        protected T GetValue<T>(Expression<Func<T>> propSelector)
        {
            return GetValue<T>(GetPropertyName(propSelector));
        }

        /// <summary>
        /// Gets the value of the specified property.
        /// </summary>
        /// <typeparam name="T">The type of the specified property.</typeparam>
        /// <param name="propName">The property name of the specified property.</param>
        /// <returns>The value of the specified property.</returns>
        private T GetValue<T>(string propName)
        {
            object result = GetValue(propName);
            if (result == null)
                return default(T);
            return (T)result;
        }

        /// <summary>
        /// Gets the value of the specified property.
        /// </summary>
        /// <param name="propName">The property name of the specified property.</param>
        /// <returns>The value of the specified property.</returns>
        protected object GetValue(string propName)
        {
            if (!InternalValues.ContainsKey(propName))
                return null;
            return InternalValues[propName];
        }

        /// <summary>
        /// Sets the value of the specified property.
        /// </summary>
        /// <typeparam name="T">The type of the specified property.</typeparam>
        /// <param name="propSelector">The member expression specifying the property to retrieve's name.</param>
        /// <param name="value">The value to set for the specified property.</param>
        protected void SetValue<T>(Expression<Func<T>> propSelector, T value)
        {
            SetValue(GetPropertyName(propSelector), value);
        }

        /// <summary>
        /// Sets the value of the specified property.
        /// </summary>
        /// <param name="propName">The name of the specified property.</param>
        /// <param name="value">The value to set for the specified property.</param>
        protected void SetValue(string propName, object value)
        {
            if (!InternalValues.ContainsKey(propName))
                InternalValues.Add(propName, value);

            InternalValues[propName] = value;
            NotifyPropertyChanged(propName);
        }

        /// <summary>
        /// Raises a PropertyChanged event for the specified property.
        /// </summary>
        /// <typeparam name="T">The property data type.</typeparam>
        /// <param name="propSelector">The member expression for the property for which to raise the event.</param>
        protected void NotifyPropertyChanged<T>(Expression<Func<T>> propSelector)
        {
            NotifyPropertyChanged(GetPropertyName(propSelector));
        }

        /// <summary>
        /// Raises a PropertyChanged event for the specified property.
        /// </summary>
        /// <param name="propName">The property name for which to raise the event.</param>
        protected virtual void NotifyPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}
