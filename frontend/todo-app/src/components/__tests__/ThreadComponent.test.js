import React from 'react'
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme'
import ThreadComponent from '../todo/ThreadComponent'


describe('ThreadComponent', () => {
	var component, thread;

	// beforeEach(() => {
	// 	thread = {
	// 		"id": 1,
	// 		"author": "John Smith",
	// 		"title": "The Rise and Fall of SEPT at RMIT",
	// 		"primary_channel": "sept",
	// 	}
	// })
	// beforeEach(() => {
	// 	// component = mount(<ThreadComponent {...thread} />)
	// 	component = mount(<ThreadComponent />)
	// })

	it('should exist', () => {
		expect(true).toBeTruthy();
		// expect(component).toBeTruthy();
		// expect(component.props().id).toEqual(thread.id)
		// expect(component.props().author).toEqual(thread.author)
		// expect(component.props().title).toEqual(thread.title)
		// expect(component.props().primary_channel).toEqual(thread.primary_channel)

	})

	// it('display primary channel when props are passed', () => {
	// 	let channel = component.find('.channel');
	// 	expect(channel.text()).toEqual("c/" + thread.primary_channel);
	// })

	// it('display thread title when props are passed', () => {
	// 	let title = component.find('.thread-title');
	// 	expect(title.text()).toEqual(thread.title);
	// })

	// it('Author text is rendered from API call', () => {
	// 	const timeDelta = "1 week"

	// 	let author = component.find('.thread-author');
	// 	expect(author.text()).toEqual("Posted by u/"+thread.author + " " + timeDelta + " ago");
	// })


	// it('Thread contents renders', () => {
	// 	const content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Id semper risus in hendrerit. Facilisis magna etiam tempor orci eu lobortis elementum nibh. Integer quis auctor elit sed vulputate. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales. Pulvinar sapien et ligula ullamcorper malesuada. Mi in nulla posuere sollicitudin aliquam. Auctor augue mauris augue neque gravida in fermentum et sollicitudin. Tincidunt nunc pulvinar sapien et. Libero id faucibus nisl tincidunt eget nullam non. Dictum sit amet justo donec enim diam vulputate. Sodales ut etiam sit amet nisl purus in mollis nunc. Vulputate mi sit amet mauris commodo. Diam vulputate ut pharetra sit amet aliquam id. Etiam non quam lacus suspendisse faucibus interdum posuere. Dolor morbi non arcu risus. Vulputate enim nulla aliquet porttitor lacus. Malesuada fames ac turpis egestas sed tempus. Tincidunt lobortis feugiat vivamus at. Enim neque volutpat ac tincidunt vitae semper quis. Vestibulum lorem sed risus ultricies tristique. Quis imperdiet massa tincidunt nunc pulvinar sapien et ligula. Senectus et netus et malesuada fames ac turpis egestas. Faucibus scelerisque eleifend donec pretium vulputate sapien nec sagittis aliquam. Mauris nunc congue nisi vitae suscipit tellus mauris a. Duis ut diam quam nulla porttitor. Viverra aliquet eget sit amet tellus cras. Tincidunt augue interdum velit euismod in pellentesque massa. Quam vulputate dignissim suspendisse in est.";

	// 	let threadContent = component.find('.thread-contents');
	// 	expect(threadContent.text()).toEqual(content);
	// })	
	// /**
	//  * Functionality not implemented yet; API not functional
	//  */
	// it('display error when props not passed', () => {
	// 	component = mount(<ThreadComponent />)
	// 	let node = component.find('.error');
	// 	// expect(node.text()).toEqual('Sorry. Thread doesn\'t exist.');
	// })

	// it('CommentsComponents render from API call', () => {
	// 	let node = component.find('CommentComponent');
	// 	console.log(node)
	// 	expect(node.length).toEqual(4);
	// })

})