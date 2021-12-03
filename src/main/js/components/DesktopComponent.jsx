import React from 'react';
import PropTypes from 'prop-types';

import TableContainer from '../containers/TableContainer.jsx';

const DesktopComponent = ({ countToBuy }) => (
    <div className="desktop-block">
        <div className="desktop-block__block">
            <h3 className="desktop-block__header">{'Составление'}</h3>
            <TableContainer mode={1} />
        </div>

        <div className="desktop-block__block">
            <h3 className="desktop-block__header">{'Покупка (' + countToBuy + ')'}</h3>
            <TableContainer mode={2} />
        </div>
    </div>
);

DesktopComponent.propTypes = {
    countToBuy: PropTypes.number.isRequired,
};

export default DesktopComponent;
