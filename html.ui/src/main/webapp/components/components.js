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
        taEditorKeydown: function(e) {
            if (e.key == 'Tab') {
                e.preventDefault();
            }
        }
    }
})
var vm = app.mount("#component-app")