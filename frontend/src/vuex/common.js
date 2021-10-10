import {router} from "../routes";

const state = {
    snackbar: {open: false, text: '', location: 'top',},
    errorMessage: '잘못된 요청입니다.',


    // ============ Rules ============ //
    emailRules: [
        v => !!v || '이메일을 작성해주세요.',
        v => /.+@.+\..+/.test(v) || '이메일 형식으로 작성해주세요.',
        v => v.search(/\s/) === -1 || '공백을 제거해주세요!'
    ],
    nameRules: [
        v => !!v || '이름을 작성해주세요.',
        v => v.search(/\s/) === -1 || '공백을 제거해주세요!'
    ],
    passwordRules: [
        v => !!v || '비밀번호를 작성해주세요',
        v => v.search(/\s/) === -1 || '공백을 제거해주세요!'
    ],

};

const getters = {};

const mutations = {
    SET_SNACKBAR(state, snackbarInfo) {
        state.snackbar.open = true;
        state.snackbar.text = snackbarInfo.text;
        state.snackbar.color = snackbarInfo.color;
        state.snackbar.location = snackbarInfo.location;
    },
    PUSH_ERROR_PAGE(state) {
        state.errorMessage = '로그인 후 이용해 주세요.';
        router.push('/user/login');
    },
};

const actions = {};

export default {mutations, state, actions, getters};
