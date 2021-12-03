import React from 'react';
import { Alert, Button } from 'react-bootstrap';

const Error = () => (
	<Alert bsStyle="danger">
		<h4>Ошибка, попробуйте еще раз</h4>
		<Button bsStyle="danger" onClick={() => window.location.reload()}>
			Обновить
		</Button>
	</Alert>
);

export default Error;
