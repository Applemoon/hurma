import { createStore, applyMiddleware, compose } from 'redux';
import ReduxThunk from 'redux-thunk';
import reducer from './reducer.js';

export const store = createStore(reducer, applyMiddleware(ReduxThunk));

// for Chrome Redux Devtools Extension
// const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
// export const store = createStore(reducer, composeEnhancers(applyMiddleware(ReduxThunk)));
