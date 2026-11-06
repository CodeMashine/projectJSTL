App.observer = new class {
    constructor() {
        this.listeners = {};
    }

    on(eventName, callBack) {
        if (!this.listeners[eventName]) {
            this.listeners[eventName] = [];
        }
        this.listeners[eventName].push(callBack);
    }

    emit(eventName, data = "") {
        this.listeners[eventName].forEach(callBack => {
            callBack(data);
        })
    }

}