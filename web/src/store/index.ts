import {createStore} from 'vuex'

declare let SessionStorage: any;
const USER = "USER";

const store = createStore({
    state: {
        // 加载，加载不到则返回空对象
        user: SessionStorage.get(USER) || {}
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
            SessionStorage.set(USER, user);
        }
    },
    actions: {},
    modules: {}
})

export default store;
