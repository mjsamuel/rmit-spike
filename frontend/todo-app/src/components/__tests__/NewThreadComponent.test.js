import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import NewThreadComponent from '../todo/NewThreadComponent'

describe('NewThreadComponent', () => {
	var component, state;

	beforeEach(() => {
		state = {
			channelId: '001',
			channelName: 'sept',
			title: '',
			body: '',
			hasSubmissionFailed: false,
			errorText: ''
		}
	})

	beforeEach(() => {
		const location = {
			state: {
				channelId: '001',
				channelName: 'sept'
			}
		}
		component = mount(<NewThreadComponent location={location}/>)
	})

	it('should exist', async() => {
		await component.update();

		expect(component).toBeTruthy();
		expect(component.state('channelId')).toEqual(state.channelId);
		expect(component.state('channelName')).toEqual(state.channelName);
	})

	it('displays an error message when title is left emty', async() => {
		component.setState({
			title: '',
			body: 'body'
		});

		component.instance().confirmClicked();
		await component.update();

		let alert = component.find('#error');
		expect(alert.text()).toEqual("Error: Missing field/s");
	})

  it('displays an error message when body is left emty', async() => {
		component.setState({
			title: 'title',
			body: ''
		});

		component.instance().confirmClicked();
		await component.update();

		let alert = component.find('#error');
		expect(alert.text()).toEqual("Error: Missing field/s");
	})

	it('displays an error message when communication with the backend fails', async() => {
		// Setting hasSubmissionFailed as true as backend has not been implemented yet
		component.setState({
			title: 'title',
			body: 'body',
			hasSubmissionFailed: true,
			errorText: "Error: Failed communicating with backend"
		});

		// component.instance().confirmClicked();
		// await component.update();

		let alert = component.find('#error');
		expect(alert.text()).toEqual("Error: Failed communicating with backend");
	})

	it('displays an success message when a thread is created successfully', async() => {
		// Setting showSuccessMessage as true as backend has not been implemented yet
		component.setState({
			title: 'title',
			body: 'body',
			hasSubmissionFailed: false,
			showSuccessMessage: true
		});

		// component.instance().confirmClicked();
		// await component.update();

		let alert = component.find('#success');
		expect(alert.text()).toEqual("Thread created successfully");
	})
})
