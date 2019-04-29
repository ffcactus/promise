import { ActionType, RegisterState } from './ConstValue';

const defaultState = {
  message: null,
  state: RegisterState.REGISTERING,
  sendingRequest: false
};

const register = (state = defaultState, action) => {
  switch (action.type) {
    case ActionType.REGISTER_START:
      return {
        ...state,
        message: null,
        sendingRequest: true
      };
    case ActionType.REGISTER_SUCCESS:
      return {
        ...state,
        sendingRequest: false,
        state: RegisterState.SUCCESS
      };
    case ActionType.REGISTER_FAILURE:
      return {
        ...state,
        sendingRequest: false,
        message: action.info.data.message
      };
    case ActionType.REGISTER_SUCCESS_CONFIRM:
      return defaultState;
    default:
      return state;
  }
};

export default register;
