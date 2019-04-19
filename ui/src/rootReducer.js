import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import session from './components/platform/login/Reducer';
export default history =>
  combineReducers({
    router: connectRouter(history),
    session
  });
