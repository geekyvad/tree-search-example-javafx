package sample

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class Main : Application() {

  override fun start(primaryStage: Stage?) {
    val loader = FXMLLoader(javaClass.getResource("sample.fxml"))
    val root: AnchorPane = loader.load()
    primaryStage?.title = "Tree search"
    primaryStage?.scene = Scene(root, 300.0, 275.0)
    primaryStage?.show()
    loader.getController<Controller>().readDirTree()
  }

}

fun main(args: Array<String>) = Application.launch(*args)

