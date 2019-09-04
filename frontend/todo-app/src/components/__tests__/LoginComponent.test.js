import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import LoginComponent from '../todo/LoginComponent'

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
		component.setState({username: "unregister-user", password: "abcd1234", hasLoginFailed: true})
    // component.instance().loginClicked();
    let dialog = component.find('#error');
    expect(dialog.text()).toEqual("Invalid Credentials or something is wrong");
	})

  it('displays a success message when login succeeds', () => {
    component.setState({username: "registered-user", password: "abcd1234", showSuccessMessage: true})
    // component.instance().loginClicked();
    let dialog = component.find('#success');
    expect(dialog.text()).toEqual("Login Sucessful");
  })
})
