const graphContainer = document.querySelector(".graph-container");
const toolbarContainer = document.querySelector(".toolbar-container");
const editForm = document.querySelector(".edit-Form");

const editFormManager = new App.editFormManager(editForm);
editFormManager.init();
const graphManager = new App.graphManager(graphContainer);
graphManager.init();
const toolbarManager = new App.toolbarManager(graphManager.graph, toolbarContainer);
toolbarManager.init();

App.observer.on("cellChosen", cell => editFormManager.setEditCell(cell));
App.observer.on("dataSaved", cell => graphManager.updateCellView(cell));