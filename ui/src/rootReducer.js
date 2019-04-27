import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import session from './components/platform/login/Reducer';
import register from './components/platform/register/Reducer';
export default history =>
  combineReducers({
    router: connectRouter(history),
    session,
    register
  });
