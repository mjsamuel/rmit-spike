import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import RegisterComponent from '../RegisterComponent'

describe('RegisterComponent', () => {
	var component, credentials;

	beforeEach(() => {
    credentials = {
        username: '',
        password: '',
    }
	})

	beforeEach(() => {
		component = mount(<RegisterComponent />)
	})

	it('displays an error when register fails', async() => {
		// Setting hasRegisterFailed as true as backend has not been implemented yet
		component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "already-registered-user",
			password: "abcd1234",
			confirmedPassword: "abcd1234",
			hasRegisterFailed: true
		})

    // component.instance().submitClicked();
		// component.update();

    let dialog = component.find('#error');
    expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

  it('displays a success message when login succeeds', async() => {
		// Setting showSuccessMessage as true as backend has not been implemented yet
    component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "user",
			password: "abcd1234",
			confirmedPassword: "abcd1234",
			showSuccessMessage: true
		})

    // component.instance().loginClicked();

    let dialog = component.find('#success');
    expect(dialog.text()).toEqual("Register Sucessful");
  })


	it('displays an error when passwords do not match', async() => {
		component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "user",
			password: "abcd1234",
			confirmedPassword: "1234abcd"
		})

		component.instance().submitClicked();
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

	it('displays an error when email is empty', async() => {
		component.setState({
			email: "",
			username: "user",
			password: "abcd1234",
			confirmedPassword: "abcd1234"
		})

		component.instance().submitClicked();
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

	it('displays an error when username is empty', async() => {
		component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "",
			password: "abcd1234",
			confirmedPassword: "abcd1234"
		})

		component.instance().submitClicked();
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

	it('displays an error when password is empty', async() => {
		component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "user",
			password: "",
			confirmedPassword: "abcd1234"
		})

		component.instance().submitClicked();
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

	it('displays an error when confirmed password is empty', async() => {
		component.setState({
			email: "s1234567@student.rmit.edu.au",
			username: "user",
			password: "abcd1234",
			confirmedPassword: ""
		})

		component.instance().submitClicked();
		await component.update();

		let dialog = component.find('#error');
		expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})
})
