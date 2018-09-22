import { combineReducers } from 'redux';
import basicReducer from './basicReducer';

// eslint-disable-next-line import/prefer-default-export
export const reducers = combineReducers({
  text: basicReducer,
});
