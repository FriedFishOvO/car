import { defineStore } from 'pinia'

const useUserInfo = defineStore('user', {
    state: () => {
        return {
            loginId: '',
            nickname: '',
            type: ''
        }
    },
    actions: {
        setUser(loginId: string, nickname: string, type: string) {
            this.loginId = loginId
            this.nickname = nickname
            this.type = type
        }
    }
})

export default useUserInfo