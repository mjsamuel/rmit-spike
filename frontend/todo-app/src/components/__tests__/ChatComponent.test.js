import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import ChatComponent from '../todo/ChatComponent'

describe('ChatComponent', () => {
	var component, chat;

	beforeEach(() => {
    chat = {
    }
	})

	beforeEach(() => {
		component = mount(<ChatComponent channelId="1234"/>)
	})

	it('should exist', async() => {

	})
})
