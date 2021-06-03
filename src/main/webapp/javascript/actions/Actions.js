import Api from './Api';

export const REQUEST_DATA_SUCCEEDED = 'REQUEST_DATA_SUCCEEDED';

export const SET_NEEDED = 'SET_NEEDED';
export const SET_NOT_NEEDED = 'SET_NOT_NEEDED';

export const SET_BOUGHT = 'SET_BOUGHT';
export const SET_NOT_BOUGHT = 'SET_NOT_BOUGHT';

export const SET_IMPORTANT = 'SET_IMPORTANT';
export const SET_NOT_IMPORTANT = 'SET_NOT_IMPORTANT';

export const ADD_ITEM = 'ADD_ITEM';
export const REMOVE = 'REMOVE';

export const MODE_1_SELECTED = 'MODE_1_SELECTED';
export const MODE_2_SELECTED = 'MODE_2_SELECTED';

export const NOW_OFFLINE = 'NOW_OFFLINE';
export const ADD_ITEM_OFFLINE = 'ADD_ITEM_OFFLINE';

export const EDIT_ITEM = 'EDIT_ITEM';

export const REQUEST_FAILED = 'REQUEST_FAILED';

const DEFAULT_ITEM = {
	id: -1,
	needed: false,
	bought: false,
	category: 'other',
};

class Actions {
	static loadData = () => dispatch => {
		Api.loadData()
			.then(result => {
				dispatch({
					type: REQUEST_DATA_SUCCEEDED,
					data: result.data,
				});
			})
			.catch(err => {
				dispatch({ type: REQUEST_FAILED, method: 'loadData', message: err });
			});
	};

	static setNeeded = id => dispatch => {
		dispatch({
			type: SET_NEEDED,
			id: id,
		});

		Api.setNeeded(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setNeeded', message: err });
		});
	};

	static setNotNeeded = id => dispatch => {
		dispatch({
			type: SET_NOT_NEEDED,
			id: id,
		});

		Api.setNotNeeded(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setNotNeeded', message: err });
		});
	};

	static setBought = id => dispatch => {
		dispatch({
			type: SET_BOUGHT,
			id: id,
		});

		Api.setBought(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setBought', message: err });
		});
	};

	static setNotBought = id => dispatch => {
		dispatch({
			type: SET_NOT_BOUGHT,
			id: id,
		});

		Api.setNotBought(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setNotBought', message: err });
		});
	};

	static setImportant = id => dispatch => {
		dispatch({
			type: SET_IMPORTANT,
			id: id,
		});

		Api.setImportant(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setImportant', message: err });
		});
	};

	static setNotImportant = id => dispatch => {
		dispatch({
			type: SET_NOT_IMPORTANT,
			id: id,
		});

		Api.setNotImportant(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'setNotImportant', message: err });
		});
	};

	static addItem = (
		name,
		needed = DEFAULT_ITEM.needed,
		category = DEFAULT_ITEM.category
	) => dispatch => {
		Api.addItem(name, category, needed)
			.then(result => {
				dispatch({
					type: ADD_ITEM,
					name: name,
					bought: result.data.bought,
					id: result.data.id,
					needed: needed,
					category: category,
				});
			})
			.catch(err => {
				!err.response
					? dispatch({
							type: ADD_ITEM_OFFLINE,
							name: name,
							bought: DEFAULT_ITEM.bought,
							needed: needed,
							category: category,
					  })
					: dispatch({ type: REQUEST_FAILED, method: 'addItem', message: err });
			});
	};

	static remove = id => dispatch => {
		dispatch({
			type: REMOVE,
			id: id,
		});

		Api.remove(id).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'remove', message: err });
		});
	};

	static edit = (id, name, category, needed) => dispatch => {
		dispatch({
			type: EDIT_ITEM,
			id: id,
			name: name,
			category: category,
			needed: needed,
		});

		Api.edit(id, name, category, needed).catch(err => {
			!err.response
				? dispatch({ type: NOW_OFFLINE })
				: dispatch({ type: REQUEST_FAILED, method: 'edit', message: err });
		});
	};

	static selectMode = mode => dispatch => {
		if (mode === 1) {
			Api.setAllNotBought().catch(err => {
				!err.response
					? dispatch({ type: NOW_OFFLINE })
					: dispatch({ type: REQUEST_FAILED, method: 'selectMode', message: err });
			});
			dispatch({ type: MODE_1_SELECTED });
		} else dispatch({ type: MODE_2_SELECTED });
	};
}

export default Actions;
