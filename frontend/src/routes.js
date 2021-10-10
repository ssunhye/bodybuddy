import DashboardLayout from './views/Layout/DashboardLayout.vue';

const check = () => (to, from, next) => {
    if (localStorage.getItem("jwt-token"))
        next('user/record');
    else
        next();
}

const check1 = () => (to, from, next) => {
    if (localStorage.getItem("jwt-token"))
        next();
    else {
        next('../user/login');
        this.$store.commit('SET_SNACKBAR', {
            text: "로그인 후 이용해주세요",
            color: '#8EC0E4',
            location: 'bottom',
        })
    }
}

export default [
    {
        mode: 'history',
        path: '/',
        redirect: 'user/login',
        component: DashboardLayout,
        children: [
            {
                path : '/user/record',
                name: 'Record',
                beforeEnter:check1(),
                component : () => import('./views/user/Record.vue')
            },
            {
                path : '/user/record/register',
                name: 'RecordRegist',
                beforeEnter:check1(),
                component : () => import('./views/user/RecordRegister.vue')
            },
            {
                path : '/user/usersettings',
                name: 'UserSettings',
                beforeEnter:check1(),
                component : () => import('./views/user/UserSettings.vue')
            },
            {
                path : '/user/challenge',
                name: 'challenge',
                beforeEnter:check1(),
                component : () => import('./views/challenge/Challenge.vue')
            },
            {
                path : '/user/challenge/register',
                name: 'chlgRegist',
                beforeEnter:check1(),
                component : () => import('./views/challenge/ChallengeRegister.vue')
            },
            {
                path : '/user/challenge/:chlgNo',
                name: 'challengeDetail',
                beforeEnter:check1(),
                component : () => import('./views/challenge/ChallengeDetail.vue')
            },
            {
                path : '/user/joinChallenge/:chlgNo',
                name: 'joinChallenge',
                beforeEnter:check1(),
                component : () => import('./views/challenge/JoinChallenge.vue')
            },
            {
                path : '/user/feed',
                name: 'Feed',
                beforeEnter:check1(),
                component : () => import('./views/feed/IndexFeed.vue')
            },
            {
                path : '/user/myfeed',
                name: 'Myfeed',
                beforeEnter:check1(),
                component : () => import('./views/feed/myfeed.vue')
            },
            {
                path : '/user/usersetting',
                name: 'userinfo',
                beforeEnter:check1(),
                component : () => import('./views/user/UserSettings.vue')
            },
            {
                path : '/user/feed/register',
                name: 'FeedRegister',
                beforeEnter:check1(),
                component : () => import('./views/feed/FeedRegister.vue')
            },
            {
                path : '/user/exercise/record',
                name: 'exerciseRecord',
                beforeEnter:check1(),
                component : () => import('./views/exercise/ExerciseRecord.vue')
            },
        ]
    },
    {
        path : '/user/login',
        name: 'Login',
        beforeEnter:check(),
        component : () => import('./views/user/Login.vue')
    },
    {
        path : '/user/register',
        name : 'Register',
        component : () => import('./views/user/Register.vue')
    },
    {
        path : '/user/test',
        name : 'test',
        component : () => import('./views/user/test.vue')
    }
]


