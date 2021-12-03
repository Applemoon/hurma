import React from 'react';
import PropTypes from 'prop-types';
import { Table } from 'react-bootstrap';

import Item from './Item.jsx';

const TableComponent = ({
	items,
	mode,
	setNeeded,
	setNotNeeded,
	setBought,
	setNotBought,
	setImportant,
	setNotImportant,
	remove,
	edit,
	categories,
}) =>
	!items.length ? (
		<p>Список пуст</p>
	) : (
		<Table>
			<tbody>
				{items.map(item => (
					<Item
						{...item}
						key={item.id}
						mode={mode}
						setNeeded={setNeeded}
						setNotNeeded={setNotNeeded}
						setBought={setBought}
						setNotBought={setNotBought}
						setImportant={setImportant}
						setNotImportant={setNotImportant}
						remove={remove}
						edit={edit}
						categories={categories}
					/>
				))}
			</tbody>
		</Table>
	);

TableComponent.propTypes = {
	items: PropTypes.arrayOf(
		PropTypes.shape({
			name: PropTypes.string.isRequired,
			bought: PropTypes.bool.isRequired,
			id: PropTypes.number.isRequired,
		}).isRequired
	).isRequired,
	mode: PropTypes.number.isRequired,
	setNeeded: PropTypes.func.isRequired,
	setNotNeeded: PropTypes.func.isRequired,
	setBought: PropTypes.func.isRequired,
	setNotBought: PropTypes.func.isRequired,
	setImportant: PropTypes.func.isRequired,
	setNotImportant: PropTypes.func.isRequired,
	remove: PropTypes.func.isRequired,
	edit: PropTypes.func.isRequired,
	categories: PropTypes.arrayOf(
		PropTypes.shape({
			name: PropTypes.string.isRequired,
			fullName: PropTypes.string.isRequired,
		}).isRequired
	).isRequired,
};

export default TableComponent;
