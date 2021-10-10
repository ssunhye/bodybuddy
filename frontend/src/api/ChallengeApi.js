import axios from "axios";

function requestSaveChallenge(challenge) {
    return axios.post(`${process.env.VUE_APP_BASEURL}/challenge`, challenge);
}