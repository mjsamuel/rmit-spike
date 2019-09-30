import React from 'react'
import ReactTestUtils from 'react-dom/test-utils';
import { shallow, mount } from 'enzyme'
import ChannelComponent from '../todo/ChannelComponent'

describe('ChannelComponent', () => {
	var component, channel;

	beforeEach(() => {
    channel = {
			channelName: "sept",
			threads: [
				{
					id: "001",
					title: "Thread Title",
					authorId: "author",
					noComments: 0,
					upspikes: 0
				},
				{
					id: "002",
					title: "The Rise and Fall of SEPT at RMIT",
					authorId: "2",
					noComments: 13,
					upspikes: 30
				}
			],
			subscribed: false
		}
	})

	beforeEach(() => {
		component = mount(<ChannelComponent match={{params: {channelId: 1234}}}/>)
	})

	it('should exist', async() => {
		await component.update();
		expect(component).toBeTruthy();
		expect(component.state('channelName')).toEqual(channel.channelName);
		expect(component.state('threads')).toEqual(channel.threads);
	})

	it('should render the channel name', async() => {
		await component.update();
		let channelName = component.find('#channel-name-banner');
		expect(channelName.text()).toEqual("c/" + channel.channelName);
	})

	it('should render a thread', async() => {
		await component.update();
		let threadTitle = component.find("#thread-list-title-" + channel.threads[0].id);
		let threadAuthor = component.find("#thread-list-author-" + channel.threads[0].id);
		expect(threadTitle.text()).toEqual(channel.threads[0].title);
		expect(threadAuthor.text()).toEqual("u/" + channel.threads[0].authorId);
	})

	it('displays unsubsribe when subscribe button is clicked', async() => {
		component.instance().subscribeClicked();
		await component.update();

		let button = component.find('#subsribe-btn');
    expect(button.text()).toEqual("Unsubscribe");
	})

	it('displays subsribe when unsubscribe button is clicked', async() => {
		component.instance().subscribeClicked();
		component.instance().subscribeClicked();
		await component.update();

		let button = component.find('#subsribe-btn');
		expect(button.text()).toEqual("Subscribe");
	})
})
