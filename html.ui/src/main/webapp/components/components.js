var app = Vue.createApp({
    data() {
        return {
            component: undefined,
            id: undefined
        }
    },
    setup() {
        console.log("Vue has been created")
    },
    methods: {
        taEditorKeydown: function (e) {
            if (this.component) {
                if (e.key == 'Tab') {
                    e.preventDefault();
                    const editor = document.getElementById("ta-editor");
                    const start = editor.selectionStart;
                    const end = editor.selectionEnd;
                    this.component = this.component.substring(0, start) + "\t" + this.component.substring(start, end) + this.component.substring(end, this.component.length);
                }
            }
        }
    }
})
var vm = app.mount("#component-app")