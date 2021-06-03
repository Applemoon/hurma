import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import {
	Button,
	Form,
	FormGroup,
	InputGroup,
	FormControl,
	Modal,
	ListGroup,
	ListGroupItem,
} from 'react-bootstrap';

class EditItemPopup extends PureComponent {
	state = {
		name: this.props.name,
		category: this.props.category,
		needed: this.props.needed,
	};

	handleNeededBtnClick = event => {
		const newNeeded = !this.state.needed;
		this.setState({ needed: newNeeded });
		if (newNeeded) {
			event.target.classList.add('active');
			event.target.classList.add('btn-success');
		} else {
			event.target.classList.remove('active');
			event.target.classList.remove('btn-success');
		}
	};

	handleInputChange = event => {
		this.setState({ name: event.target.value });
	};

	handleCategoryClick = event => {
		this.setState({ category: event.target.value });
	};

	handleSubmit = event => {
		event.preventDefault();
		this.props.handleEditingOk(this.state.name, this.state.category, this.state.needed);
	};

	render() {
		const { show, handleEditingOk, handleEditingCancel, categories } = this.props;
		const defaultName = this.props.name;
		const { name, category, needed } = this.state;
		const { handleNeededBtnClick, handleInputChange, handleCategoryClick, handleSubmit } = this;

		return (
			<Modal show={show} onHide={handleEditingCancel}>
				<Modal.Header closeButton>
					<Modal.Title>{'Редактировать "' + defaultName + '"'}</Modal.Title>
				</Modal.Header>

				<Modal.Body>
					<Form onSubmit={handleSubmit}>
						<FormGroup>
							<InputGroup>
								<FormControl
									type="text"
									defaultValue={name}
									placeholder={name}
									onChange={handleInputChange}
									autoFocus
								/>
								<InputGroup.Button>
									<Button
										onClick={handleNeededBtnClick}
										bsStyle={needed ? 'success' : 'default'}
										active={needed}
										style={{ outline: 'none' }}>
										Нужен
									</Button>
								</InputGroup.Button>
							</InputGroup>
						</FormGroup>
					</Form>
					<ListGroup>
						{categories.map(cat => (
							<ListGroupItem
								active={cat.name === category}
								key={cat.name}
								value={cat.name}
								onClick={handleCategoryClick}>
								{cat.fullName}
							</ListGroupItem>
						))}
					</ListGroup>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={handleEditingCancel}>Отмена</Button>
					<Button
						bsStyle="success"
						onClick={event => handleEditingOk(name, category, needed)}>
						OK
					</Button>
				</Modal.Footer>
			</Modal>
		);
	}
}

EditItemPopup.propTypes = {
	name: PropTypes.string.isRequired,
	category: PropTypes.string.isRequired,
	needed: PropTypes.bool.isRequired,
	show: PropTypes.bool.isRequired,
	handleEditingOk: PropTypes.func.isRequired,
	handleEditingCancel: PropTypes.func.isRequired,
	categories: PropTypes.arrayOf(
		PropTypes.shape({
			name: PropTypes.string.isRequired,
			fullName: PropTypes.string.isRequired,
		}).isRequired
	).isRequired,
};

export default EditItemPopup;
