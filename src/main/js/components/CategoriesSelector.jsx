import React from 'react';
import PropTypes from 'prop-types';
import { ControlLabel, FormControl } from 'react-bootstrap';

const CategoriesSelector = ({ onSelectChange, category, categories }) => (
	<div>
		<ControlLabel>Категория:</ControlLabel>{' '}
		<FormControl componentClass="select" onChange={onSelectChange} value={category}>
			{categories.map(category => (
				<option value={category.name} key={category.name}>
					{category.fullName}
				</option>
			))}
		</FormControl>
	</div>
);

CategoriesSelector.propTypes = {
	onSelectChange: PropTypes.func.isRequired,
	category: PropTypes.string.isRequired,
	categories: PropTypes.arrayOf(
		PropTypes.shape({
			name: PropTypes.string.isRequired,
			fullName: PropTypes.string.isRequired,
		}).isRequired
	).isRequired,
};

export default CategoriesSelector;
