App.toolbarManager = class {
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
            const questName = this.getQuestName();
            const questXml = this.getXML();
            const questJson = this.getJson();
            this.sendToServer(questName, questXml,questJson);
        });
    }

    async sendToServer(questName, questXml,questJson) {
        const params = new URLSearchParams ();
        params.append('questName', questName);
        params.append('questXml', questXml);
        params.append('questJson', questJson);

        try {
            const response = await fetch(`/save-quest`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                },
                body: params
            });

            if (response.ok) {
                console.log('Quest saved');
            } else {
                console.error('Error quest save:', response.statusText);
            }
        } catch (error) {
            console.error('Network error:', error);
        }

    }
    getXML() {
        const codec = new mxCodec();
        const node = codec.encode(this.graph.getModel());
        const xmlString = mxUtils.getPrettyXml(node);
        return xmlString;
    }

    getJson() {
        let model = this.graph.getModel();
        let preJson = {};

        for (let key in Object.keys(model.cells)) {
        // for (let key of Object.keys(model.cells)) {
            let cell = model.cells[key];
            let stepData = cell.value;
            if (!stepData) continue;
            preJson[cell.id] = {
                stepName: stepData.stepName,
                questionText: stepData.questionText,
                isWin: stepData.isWin,
                isLose: stepData.isLose,
                isPassable: stepData.isPassable,
                choices: []
            }

            for (let i = 0; i < cell.edges.length; i++) {
                let targetId = cell.edges[i].target.id;
                if(targetId !== cell.id){
                    preJson[cell.id].choices.push(targetId);
                }
            }
        }
        return JSON.stringify(preJson,null,2);;
    }

    getQuestName() {
        return document.querySelector(".quest-name").value;
    }
}

