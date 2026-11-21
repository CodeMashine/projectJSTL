App.questLoader = class {
    check() {
        let questNameInput = document.querySelector(".quest-name");
        let questName = document.querySelector(".quest-name-inlet").innerText;
        let questXML = document.querySelector(".quest-xml-inlet").innerText;
        if (questName) {
            questNameInput.value = questName;
            App.observer.emit("questLoadToEdit", questXML);
        }
    }
}