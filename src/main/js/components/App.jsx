import React, { PureComponent } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import Error from './Error.jsx';
import AddItemForm from './AddItemForm.jsx';
import Actions from '../actions/Actions';
import OfflineWarning from './OfflineWarning.jsx';
import MobileComponent from './MobileComponent.jsx';
import DesktopComponent from './DesktopComponent.jsx';

class App extends PureComponent {
    componentDidMount() {
        this.props.loadData();
    }

    render() {
        const { items, error, mode, offline, loading, selectMode } = this.props;
        const isMobile = window.innerWidth <= 600;
        const countToBuy = items.filter(item => item.needed && !item.bought).length;

        return error ? (
            <Error />
        ) : loading ? (
            <p>Загрузка...</p>
        ) : (
            <div>
                {offline && <OfflineWarning />}
                {isMobile ? (
                    <MobileComponent mode={mode} selectMode={selectMode} countToBuy={countToBuy}/>
                ) : (
                    <DesktopComponent countToBuy={countToBuy}/>
                )}
                <AddItemForm items={items} forceNeeded={mode === 2} />
            </div>
        );
    }
}

const mapStateToProps = state => ({
    items: state.items,
    error: state.error,
    mode: state.mode,
    offline: state.offline,
    loading: state.loading,
});

const mapDispatchToProps = dispatch => ({
    selectMode: bindActionCreators(Actions.selectMode, dispatch),
    loadData: bindActionCreators(Actions.loadData, dispatch),
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(App);
