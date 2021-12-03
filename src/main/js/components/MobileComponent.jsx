import React from 'react';
import PropTypes from 'prop-types';

import ModeSelector from './ModeSelector.jsx';
import TableContainer from '../containers/TableContainer.jsx';

const MobileComponent = ({ mode, selectMode, countToBuy }) => (
    <div>
        <ModeSelector mode={mode} selectMode={selectMode} countToBuy={countToBuy}/>
        <TableContainer mode={mode} />
        <img src="/frontend/cards.png" width="100%" />
    </div>
);

MobileComponent.propTypes = {
    mode: PropTypes.number.isRequired,
    selectMode: PropTypes.func.isRequired,
    countToBuy: PropTypes.number.isRequired,
};

export default MobileComponent;
