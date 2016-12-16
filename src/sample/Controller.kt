package sample

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.TreeCell
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.util.Callback
import java.io.File

class Controller {

  @FXML
  lateinit var search: TextField

  @FXML
  lateinit var treeView: TreeView<Node>

  lateinit var originalNodeTree: Node


  fun readDirTree() {
    originalNodeTree = traverseDir(File(System.getProperty("user.dir")))!!
    treeView.root = buildTreeItem(originalNodeTree)
    treeView.cellFactory = Callback { return@Callback ColoredNodeCell() }

    // Add change listener to search input
    search.addEventHandler(ActionEvent.ACTION, {
      val searchText = (it.source as TextField).text
      if (searchText.isEmpty()) {
        treeView.root = buildTreeItem(originalNodeTree)
      } else {
        treeView.root = buildTreeItem(originalNodeTree.search(searchText))
      }
    })
  }

  private fun traverseDir(dirOrFile: File): Node? {
    val node = Node().apply {
      name = dirOrFile.name
      children = mutableListOf()
    }
    if (dirOrFile.isFile) {
      return node
    }
    val fileList = dirOrFile.listFiles()
    if (fileList != null && !fileList.isEmpty()) {
      node.children = fileList.mapNotNull { traverseDir(it) }.toMutableList()
    }
    return node
  }

  private fun buildTreeItem(node: Node?): TreeItem<Node>? {
    node ?: return null
    val item = TreeItem(node)
    node.children.forEach { item.children.add(buildTreeItem(it)) }
    item.expandedProperty().value = node.expanded
    return item
  }

}
