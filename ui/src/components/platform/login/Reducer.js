import {ActionType, LoginState} from './ConstValue';

const defaultSessionState = {
    hostname:
        process.env.NODE_ENV === 'development'
            ? window.location.hostname + ':3000'
            : window.location.hostname,
    state: LoginState.LOGOUT,
    token: null,
    message: null
};

const session = (state = defaultSessionState, action) => {
    switch (action.type) {
        case ActionType.LOGIN_START:
            return {
                ...state,
                state: LoginState.LOGGING,
                token: null,
                message: null
            };
        case ActionType.LOGIN_SUCCESS:
            return {
                ...state,
                state: LoginState.LOGGED,
                hostname: action.info.hostname,
                token: action.info.response.headers['authorization']
            };
        case ActionType.LOGIN_FAILURE:
            return {
                ...state,
                state: LoginState.LOGOUT,
                token: null,
                message: action.info.message
            };
        case ActionType.LOGOUT_START:
            return state;
        case ActionType.LOGOUT_SUCCESS:
            return defaultSessionState;
        case ActionType.LOGOUT_FAILURE:
            return state;
        default:
            return state;
    }
};

export default session;
