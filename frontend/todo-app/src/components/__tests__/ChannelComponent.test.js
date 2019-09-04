import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import ChannelComponent from '../todo/ChannelComponent'

describe('LoginComponent', () => {
	var component, channel;

	beforeEach(() => {
    channel = {
			channelName: "c/sept",
			threads: [
				{
					id: "001",
					title: "Thread Title",
					author: "u/author",
					noComments: 0,
					upspikes: 0
				},
				{
					id: "002",
					title: "The Rise and Fall of SEPT at RMIT",
					author: "u/john-smith",
					noComments: 13,
					upspikes: 30
				}
			]
    }
	})

	beforeEach(() => {
		component = mount(<ChannelComponent match={{params: {channelId: 1234}}}/>)
	})

	it('should exist', () => {
		expect(component).toBeTruthy();
	})

	it('displays unsubsribe when subscribe button is clicked', () => {
		component.instance().subscribeClicked();
		let button = component.find('#subsribe-btn');
    expect(button.text()).toEqual("Unsubscribe");
	})

	it('displays subsribe when unsubscribe button is clicked', () => {
		component.instance().subscribeClicked();
		component.instance().subscribeClicked();
		let button = component.find('#subsribe-btn');
		expect(button.text()).toEqual("Subscribe");
	})
})
