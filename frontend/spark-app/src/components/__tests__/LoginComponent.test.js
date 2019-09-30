import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import LoginComponent from '../LoginComponent'

describe('LoginComponent', () => {
	var component, credentials;

	beforeEach(() => {
    credentials = {
        username: '',
        password: '',
    }
	})

	beforeEach(() => {
		component = mount(<LoginComponent />)
	})

	it('displays an error when log-in fails', () => {
		// Setting state as failed as backend has not been implemented yet
		component.setState({username: "unregister-user", password: "abcd1234", hasLoginFailed: true})
    // component.instance().loginClicked();

    let dialog = component.find('#error');
    expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})
	
	it('displays a success message when login succeeds', () => {
		// Setting state as succeeded as backend has not been implemented yet
		component.setState({username: "registered-user", password: "abcd1234", showSuccessMessage: true})
		// component.instance().loginClicked();

		let dialog = component.find('#success');
		expect(dialog.text()).toEqual("Login Sucessful");
	})

	it('displays an error when the username field is empty', async() => {
		component.setState({username: "", password: "abcd1234"})
		component.instance().loginClicked()
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

	it('displays an error when the password field is empty', async() => {
		component.setState({username: "user", password: ""})
		component.instance().loginClicked()
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

})
