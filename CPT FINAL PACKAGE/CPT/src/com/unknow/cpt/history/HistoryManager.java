/**
 * Provide some basic database operation with history table.
 * insert, delete, select.
 * @author Mali
 */
package com.unknow.cpt.history;

import java.util.List;

/**
 * @author Mali
 */
public interface HistoryManager {
  public boolean hasItems();
  public void deleteItem(String ID);
  public void addItem(Object o);
  public List<Object> getItems();
  public Object getItem(String ID);
}
