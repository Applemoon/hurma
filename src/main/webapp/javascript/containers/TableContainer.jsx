import React, { PureComponent } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import TableComponent from '../components/TableComponent.jsx';
import Actions from '../actions/Actions';

class TableContainer extends PureComponent {
	customSorting = (itemA, itemB) => {
	    var diff =
	        this.props.categories.find(category => category.name === itemA.category).position -
			this.props.categories.find(category => category.name === itemB.category).position;
		if (diff !== 0) return diff;

        diff = (itemB.needed ? 1 : 0) - (itemA.needed ? 1 : 0)
        if (diff !== 0) return diff;

        return (itemB.important ? 1 : 0) - (itemA.important ? 1 : 0)
	};

	getItems() {
		const { items, mode } = this.props;

		if (mode === 1) return items.sort(this.customSorting);

		const notBought = items
			.filter(item => item.needed && !item.bought)
			.sort(this.customSorting);
		const bought = items.filter(item => item.needed && item.bought).sort(this.customSorting);
		return notBought.concat(bought);
	}

	render() {
		return <TableComponent {...this.props} items={this.getItems()} />;
	}
}

const mapStateToProps = state => ({
	items: state.items,
	error: state.error,
	offline: state.offline,
	loading: state.loading,
	categories: state.categories,
});

const mapDispatchToProps = dispatch => ({
	setNeeded: bindActionCreators(Actions.setNeeded, dispatch),
	setNotNeeded: bindActionCreators(Actions.setNotNeeded, dispatch),
	setBought: bindActionCreators(Actions.setBought, dispatch),
	setNotBought: bindActionCreators(Actions.setNotBought, dispatch),
	setImportant: bindActionCreators(Actions.setImportant, dispatch),
	setNotImportant: bindActionCreators(Actions.setNotImportant, dispatch),
	remove: bindActionCreators(Actions.remove, dispatch),
	edit: bindActionCreators(Actions.edit, dispatch),
});

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(TableContainer);
