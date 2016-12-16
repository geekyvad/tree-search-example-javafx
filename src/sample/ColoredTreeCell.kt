package sample

import javafx.scene.control.TreeCell

class ColoredNodeCell : TreeCell<Node>() {
  override fun updateItem(item: Node?, empty: Boolean) {
    super.updateItem(item, empty)
    if (empty) {
      text = null
      graphic = null
      styleClass.remove("matched-tree-item")
    } else if (item != null) {
      text = item.name
      graphic = treeItem.graphic
      if (item.matched) {
        styleClass.add("matched-tree-item")
      } else {
        styleClass.remove("matched-tree-item")
      }
    }
  }
}