import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import { reducers } from './reducers';
import { sagas } from './sagas';

const middlewares = [];

const sagaMiddleware = createSagaMiddleware();
middlewares.push(sagaMiddleware);

const middleware = applyMiddleware(...middlewares);
const store = createStore(
  reducers,
  middleware,
  // eslint-disable-next-line no-underscore-dangle,no-undef
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

sagaMiddleware.run(sagas);
// eslint-disable-next-line import/prefer-default-export
export { store };
