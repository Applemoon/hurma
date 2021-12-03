import React from 'react';
import { Alert } from 'react-bootstrap';

const OfflineWarning = () => (
	<Alert bsStyle="warning">
		<strong>Вы теперь оффлайн</strong>
		<p>Ваши действия не будут сохранены</p>
	</Alert>
);

export default OfflineWarning;
