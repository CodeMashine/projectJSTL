App.editFormManager = class {
    cell = null;

    constructor(container) {
        this.form = container;
        this.stepName = container.querySelector(".stepName");
        this.questionText = container.querySelector(".questionText");
        this.isWin = container.querySelector(".isWin");
        this.isLose = container.querySelector(".isLose");
        this.isPassable = container.querySelector(".isPassable");
    }

    init() {
        this.removeDefaultSubmit();
    }

    removeDefaultSubmit() {
        this.form.addEventListener("submit", (e) => {
            e.preventDefault();
            this.dataHandler();
        })
    }

    setEditCell(cell) {
        this.cell = cell;
        const data = cell.value
        this.stepName.value = data.stepName;
        this.questionText.value = data.questionText;
        this.isWin.checked = data.isWin;
        this.isLose.checked = data.isLose;
        this.isPassable.checked = data.isPassable;
    }

    dataHandler() {
        const data = this.cell.value;
        data.stepName = this.stepName.value;
        data.questionText = this.questionText.value;
        data.isWin = this.isWin.checked;
        this.isLose.checked = data.isLose = this.isLose.checked;
        this.isPassable.checked = data.isPassable = this.isPassable.checked;
        App.observer.emit("dataSaved" , this.cell);
    }
}
