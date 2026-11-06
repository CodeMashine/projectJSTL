App.toolbarManager = class {
    graph;

    constructor(graph, container) {
        this.graph = graph;
        this.toolbar = new mxToolbar(container);
    }

    init() {
        this.toolbar.addItem('Add Block', null, () => {
            console.log(this.graph);
            const parent = this.graph.getDefaultParent();
            this.graph.getModel().beginUpdate();
            try {
                this.graph.insertVertex(parent, null, new App.cellData(), 100, 100, 80, 30);
            } finally {
                this.graph.getModel().endUpdate();
            }
        });

        this.toolbar.addItem('Add Line', null, () => {
            const parent = this.graph.getDefaultParent();
            const selectedCells = this.graph.getSelectionCells();
            const parentCell = selectedCells[0];
            const childCell = selectedCells[1];
            this.graph.getModel().beginUpdate();
            try {
                this.graph.insertEdge(parent, null, '', parentCell, childCell);
            } finally {
                this.graph.getModel().endUpdate();
            }
        });

        this.toolbar.addItem('Delete', null, () => {
            const cells = this.graph.getSelectionCells();
            if (cells.length > 0) {
                this.graph.removeCells(cells);
            }
        });

        this.toolbar.addItem('Save', null, () => {
            const encoder = new mxCodec();
            const node = encoder.encode(this.graph.getModel());
            const xmlString = mxUtils.getXml(node);
            console.log(xmlString);
        });

    }
}


