import axios from 'axios';

axios.defaults.xsrfCookieName = 'csrftoken';
axios.defaults.xsrfHeaderName = 'X-CSRFToken';

class Api {
	static loadData() {
		return axios.get('/api/categories');
	}

	static addItem(name, category, needed) {
		return axios.post('/api/items', { name: name, category: category, needed: needed });
	}

	static setNeeded(id) {
		return axios.patch(`/api/items/${id}`, { needed: true });
	}

	static setNotNeeded(id) {
		return axios.patch(`/api/items/${id}`, { needed: false });
	}

	static setBought(id) {
		return axios.patch(`/api/items/${id}`, { bought: true });
	}

	static setNotBought(id) {
		return axios.patch(`/api/items/${id}`, { bought: false });
	}

	static setImportant(id) {
		return axios.patch(`/api/items/${id}`, { important: true });
	}

	static setNotImportant(id) {
		return axios.patch(`/api/items/${id}`, { important: false });
	}

	static remove(id) {
		return axios.delete('/api/items/' + id);
	}

	static edit(id, name, category, needed) {
		return axios.patch(`/api/items/${id}`, { name: name, category: category, needed: needed });
	}

	static setAllNotBought() {
		return axios.patch('/api/items/all-not-bought');
	}
}

export default Api;
