App.cellData = class {
    constructor(stepName = "step name", questionText = "question text", isWin = false, isLose = false, isPassable = true) {
        this.stepName = stepName;
        this.questionText = questionText;
        this.isWin = isWin;
        this.isLose = isLose;
        this.isPassable = isPassable;
    }

    toString() {
        return this.stepName;
    }
}

