App.graphManager = class {
    constructor(container, editFormManager) {
        this.graph = new mxGraph(container);
        this.editFormManager = editFormManager;
    }

    init() {
        this.graph.setConnectable(false);
        this.graph.isCellEditable = (cell) => false;
        new mxRubberband(this.graph);

        const parent = this.graph.getDefaultParent();

        this.graph.getModel().beginUpdate();
        try {
            const v1 = this.graph.insertVertex(parent, null,
                new App.cellData(), 20, 20, 80, 30);
            const v2 = this.graph.insertVertex(parent, null,
                new App.cellData(), 200, 150, 80, 30);
            const e1 = this.graph.insertEdge(parent, null, '', v1, v2);
        } finally {
            this.graph.getModel().endUpdate();
        }

        this.addDeleteListener();
        this.addDoubleClickListener();
        // this.clickListener();
    }

    addDeleteListener() {
        mxEvent.addListener(window, 'keydown', (evt) => {
            if (evt.key === 'Delete') {
                const cells = this.graph.getSelectionCells();

                if (cells.length > 0) {
                    this.graph.removeCells(cells);
                    evt.preventDefault();
                }
            }
        });
    }

    addDoubleClickListener() {
        this.graph.addListener(mxEvent.DOUBLE_CLICK, (sender, evt) => {
            const cell = evt.getProperty('cell');
            if (cell && this.graph.getModel().isVertex(cell)) {
                App.observer.emit("cellChosen", cell)
            }
        });
    }

    clickListener() {
        this.graph.addListener(mxEvent.CLICK, (sender, evt) => {
            const cell = evt.getProperty('cell');

            if (cell && this.graph.getModel().isVertex(cell)) {
                console.log("клик по блоку:", cell.value);
                this.editFormManager.clearForm();
            }
        });
    }

    updateCellView(cell) {
        this.graph.getModel().beginUpdate();
        try {
            this.graph.getModel().setValue(cell, cell.value);
        } finally {
            this.graph.getModel().endUpdate();
        }
    }
}
