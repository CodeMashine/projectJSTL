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
        this.customSubmit();
    }

    customSubmit() {
        this.form.addEventListener("submit", (e) => {
            e.preventDefault();
            this.dataHandler();
            App.observer.emit("dataSaved", this.cell);
            App.observer.emit("clearSelection");
            this.clearForm();
        })
    }

    setEditCell(cell) {
        console.log(cell);
        this.cell = cell;
        const data = cell.value;
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
        data.isLose = this.isLose.checked;
        data.isPassable = this.isPassable.checked;
    }

    clearForm() {
        this.stepName.value = "";
        this.questionText.value = "";
        this.isWin.checked = false;
        this.isLose.checked = false;
        this.isPassable.checked = true;
        this.cell = null;
    }
}
