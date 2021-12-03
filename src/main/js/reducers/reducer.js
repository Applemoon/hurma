import {
	REQUEST_DATA_SUCCEEDED,
	SET_NEEDED,
	SET_NOT_NEEDED,
	SET_BOUGHT,
	SET_NOT_BOUGHT,
	SET_IMPORTANT,
	SET_NOT_IMPORTANT,
	ADD_ITEM,
	REMOVE,
	EDIT_ITEM,
	MODE_1_SELECTED,
	MODE_2_SELECTED,
	NOW_OFFLINE,
	ADD_ITEM_OFFLINE,
	REQUEST_FAILED,
} from '../actions/Actions';

const initialState = {
	items: [],
	loading: true,
	error: false,
	mode: 1,
	offline: false,
	categories: [],
};

const reducer = (state = initialState, action) => {
	switch (action.type) {
		case REQUEST_DATA_SUCCEEDED: {
			const categories = action.data.sort((a, b) => a.position - b.position);
			const items = categories
				.map(category => {
					const items = category.items;
					delete category.items;
					return items;
				})
				.flat();

			return Object.assign({}, state, {
				categories: categories,
				items: items,
				loading: false,
			});
		}
		case SET_NEEDED: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.needed = true;
					return item;
				}),
			});
		}
		case SET_NOT_NEEDED: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.needed = false;
					return item;
				}),
			});
		}
		case SET_BOUGHT: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.bought = true;
					return item;
				}),
			});
		}
		case SET_NOT_BOUGHT: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.bought = false;
					return item;
				}),
			});
		}
		case SET_IMPORTANT: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.important = true;
					return item;
				}),
			});
		}
		case SET_NOT_IMPORTANT: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) item.important = false;
					return item;
				}),
			});
		}
		case ADD_ITEM: {
			const newItem = {
				name: action.name,
				bought: action.bought,
				id: action.id,
				needed: action.needed,
				important: false,
				category: action.category,
			};
			return Object.assign({}, state, {
				items: state.items.concat(newItem),
			});
		}
		case REMOVE: {
			return Object.assign({}, state, {
				items: state.items.filter(item => {
					return item.id !== action.id;
				}),
			});
		}
		case EDIT_ITEM: {
			return Object.assign({}, state, {
				items: state.items.map(item => {
					if (item.id == action.id) {
						item.name = action.name;
						item.category = action.category;
						item.needed = action.needed;
					}
					return item;
				}),
			});
		}
		case MODE_1_SELECTED: {
			return Object.assign({}, state, {
				mode: 1,
				items: state.items.map(item => {
					item.bought = false;
					return item;
				}),
			});
		}
		case MODE_2_SELECTED: {
			return Object.assign({}, state, {
				mode: 2,
			});
		}
		case REQUEST_FAILED: {
			console.error('Reduce error at method:', action.method);
			console.error(action.message);
			return {
				loading: false,
				error: true,
			};
		}
		case NOW_OFFLINE: {
			return Object.assign({}, state, {
				offline: true,
			});
		}
		case ADD_ITEM_OFFLINE: {
			const newItem = {
				name: action.name,
				bought: action.bought,
				id: -1,
				needed: action.needed,
				important: false,
			};
			return Object.assign({}, state, {
				items: state.items.concat(newItem),
				offline: true,
			});
		}
		default:
			return state;
	}
};

export default reducer;
