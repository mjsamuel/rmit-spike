import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import ChatComponent from '../ChatComponent'

describe('ChatComponent', () => {
	var component, chat;

	beforeEach(() => {
		chat = [
			{
				username: "username",
				content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor',
				timeNumber: 10,
				timeUnit: "minutes"
			},
			{
				username: "username",
				content: 'Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh',
				timeNumber: 9,
				timeUnit: "minutes"
			},
			{
				username: "username",
				content: 'Integer quis auctor elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales',
				timeNumber: 4,
				timeUnit: "minutes"
			},
			{
				username: "username",
				content: 'Pulvinar sapien et ligula ullamcorper malesuada',
				timeNumber: 2,
				timeUnit: "minutes"
			},
			{
				username: "username",
				content: 'Mi in nulla posuere sollicitudin aliquam',
				timeNumber: 12,
				timeUnit: "seconds"
			}
		];
	})

	beforeEach(() => {
		component = mount(<ChatComponent channelId="1234"/>)
	})

	it('should exist', async() => {
		await component.update();
		expect(component).toBeTruthy();
		expect(component.state('messages')).toEqual(chat);
	})

	it('should not send a message if input field is empty', async() => {
		component.setState({
			currentMessage: ""
		});
		component.instance().handleSentMessage();
		await component.update();

		expect(component.state('messages')).toEqual(chat);
	})

	it('should send a message if input field is filled in', async() => {
		component.setState({
			currentMessage: "test message"
		});
		component.instance().handleSentMessage();
		await component.update();

		let newMessage = {
			username: "currentUser",
			content: "test message",
			timeNumber: 0,
			timeUnit: "seconds"
		};

		let updatedMessages = chat.concat(newMessage);

		expect(component.state('messages')).toEqual(updatedMessages);
	})
})
