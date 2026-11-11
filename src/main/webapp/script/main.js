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
App.observer.on("clearSelection", cell => graphManager.clearSelection());

// <mxGraphModel>
//     <root>
//         <mxCell id="0"/>
//         <mxCell id="1" parent="0"/>
//         <mxCell id="2" vertex="1" parent="1"><StepData as="value"/>
//             <mxGeometry x="20" y="20" width="80" height="30" as="geometry"/>
//         </mxCell>
//         <mxCell id="3" vertex="1" parent="1"><StepData as="value"/>
//             <mxGeometry x="200" y="150" width="80" height="30" as="geometry"/>
//         </mxCell>
//         <mxCell id="4" value="" edge="1" parent="1" source="2" target="3">
//             <mxGeometry relative="1" as="geometry"/>
//         </mxCell>
//     </root>
// </mxGraphModel>