using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System;
using System.Linq;
using System.Data;
using System.Data.Linq;
using System.Collections;
using System.Windows.Threading;
using System.Collections.Specialized;

namespace CPT.ViewModels
{
    /// <summary>
    /// Combines ObservableCollection and IList so DataHelper recognises it as a normal IList.
    /// </summary>
    /// <typeparam name="T">The type of list element.</typeparam>
    public class ObservableList<T> : ObservableCollection<T>, IList<T>, IList
    {
        /// <summary>
        /// Adds a range of elements to the current list.
        /// </summary>
        /// <param name="range"></param>
        public void AddRange(IEnumerable<T> range)
        {
            IEnumerator<T> enumerator = range.GetEnumerator();
            while (enumerator.MoveNext())
                Add(enumerator.Current);
        }

        /// <summary>
        /// Clears all elements from the list and refills it with the specified range of elements.
        /// </summary>
        /// <param name="range"></param>
        public void RefreshList(IEnumerable<T> range)
        {
            Clear();
            AddRange(range);
        }
    }

    /// <summary>
    /// Combines ObservableCollection and IList so DataHelper recognises it as a normal collection.
    /// </summary>
    public class ObservableList : ObservableList<object>
    {
    }
}
