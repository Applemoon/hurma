import React, { PureComponent } from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { Button, Form, FormGroup, InputGroup, FormControl } from 'react-bootstrap';

import Actions from '../actions/Actions';
import CategoriesSelector from './CategoriesSelector.jsx';

class AddItemForm extends PureComponent {
	state = { name: '', category: 'other', needed: true };
	inputId = 'newItemInput';

	handleSubmit = event => {
		const { name, category, needed } = this.state;
		const { items, addItem, forceNeeded } = this.props;

		event.preventDefault();
		const foundItem = items.find(item => item.name.toLowerCase() === name.toLowerCase());
		if (!foundItem) {
			const final_needed = needed || forceNeeded;
			addItem(name, final_needed, category);
		}
		this.setState({ name: '' });
		this.setFocutOnInput();
	};

	handleInputChange = event => {
		this.setState({ name: event.target.value });
	};

	handleSelectorChange = event => {
		this.setState({ category: event.target.value });
	};

	handleNeededBtn = event => {
		const new_needed = !this.state.needed || this.props.forceNeeded;
		this.setState({ needed: new_needed });
		if (new_needed) {
			event.target.classList.remove('active');
			event.target.classList.remove('btn-success');
		} else {
			event.target.classList.add('active');
			event.target.classList.add('btn-success');
		}
	};

	setFocutOnInput = () => {
		if (document.activeElement.tagName !== 'INPUT')
			document.getElementById(this.inputId).focus();
	};

	componentDidMount() {
		document.addEventListener('keydown', this.setFocutOnInput, false);
	}

	render() {
		const { name, category, needed } = this.state;
		const { categories, forceNeeded } = this.props;
		const { inputId, handleSubmit, handleInputChange, handleSelectorChange } = this;

		return (
			<Form inline onSubmit={handleSubmit}>
				<FormGroup>
					<InputGroup style={{ marginBottom: '5px' }}>
						<FormControl
							id={inputId}
							type="text"
							value={name}
							placeholder="Новый продукт"
							onChange={handleInputChange}
						/>
						<InputGroup.Button>
							{!forceNeeded ? (
								<Button
									onClick={this.handleNeededBtn}
									bsStyle={needed ? 'success' : 'default'}
									active={needed}
									style={{ outline: 'none' }}>
									Нужен
								</Button>
							) : null}
							<Button bsStyle="primary" type="submit">
								Добавить
							</Button>
						</InputGroup.Button>
					</InputGroup>
					<CategoriesSelector
						onSelectChange={handleSelectorChange}
						category={category}
						categories={categories}
					/>
				</FormGroup>
			</Form>
		);
	}
}

const mapStateToProps = state => ({
	mode: state.mode,
	items: state.items,
	categories: state.categories,
});

const mapDispatchToProps = dispatch => ({
	addItem: bindActionCreators(Actions.addItem, dispatch),
});

AddItemForm = connect(
	mapStateToProps,
	mapDispatchToProps
)(AddItemForm);

export default AddItemForm;
