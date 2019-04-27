import { ActionType, RegisterState } from './ConstValue';

const defaultState = {
  hostname: null,
  state: RegisterState.INPUTTING
};

const register = (state = defaultState, action) => {
  switch (action.type) {
    case ActionType.REGISTER_START:
      return {
        ...state,
        state: RegisterState.REGISTERING
      };
    case ActionType.REGISTER_SUCCESS:
      return {
        ...state,
        state: RegisterState.SUCCESS
      };
    case ActionType.REGISTER_FAILURE:
      return {
        ...state,
        state: RegisterState.ERROR,
        error: action.info
      };
    default:
      return state;
  }
};

export default register;
