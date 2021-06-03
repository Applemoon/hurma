import React from 'react';
import { Nav, NavItem } from 'react-bootstrap';
import PropTypes from 'prop-types';

const style = { paddingBottom: '20px' };

const ModeSelector = ({ mode, selectMode, countToBuy }) => (
	<Nav bsStyle="pills" activeKey={mode} onSelect={key => selectMode(key)} style={style}>
		<NavItem eventKey={1}> Составление </NavItem>
		<NavItem eventKey={2}> {'Покупка (' + countToBuy + ')'} </NavItem>
	</Nav>
);

ModeSelector.propTypes = {
	mode: PropTypes.number.isRequired,
	selectMode: PropTypes.func.isRequired,
	countToBuy: PropTypes.number.isRequired,
};

export default ModeSelector;
