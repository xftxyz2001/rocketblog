let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'AdminController',
    order: '1',
    link: '后台管理相关',
    desc: '后台管理相关',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: '/admin/users',
    desc: '',
});
api[0].list[0].list.push({
    order: '2',
    deprecated: 'false',
    url: '/admin/user/{id}',
    desc: '',
});
api[0].list[0].list.push({
    order: '3',
    deprecated: 'false',
    url: '/admin/user',
    desc: '',
});
api[0].list[0].list.push({
    order: '4',
    deprecated: 'false',
    url: '/admin/user',
    desc: '',
});
api[0].list[0].list.push({
    order: '5',
    deprecated: 'false',
    url: '/admin/user/{id}',
    desc: '',
});
api[0].list[0].list.push({
    order: '6',
    deprecated: 'false',
    url: '/admin/search/username/{name}',
    desc: '',
});
api[0].list[0].list.push({
    order: '7',
    deprecated: 'false',
    url: '/admin/search/email/{email}',
    desc: '',
});
api[0].list[0].list.push({
    order: '8',
    deprecated: 'false',
    url: '/admin/blogs',
    desc: '',
});
api[0].list[0].list.push({
    order: '9',
    deprecated: 'false',
    url: '/admin/search/blog',
    desc: '',
});
api[0].list[0].list.push({
    order: '10',
    deprecated: 'false',
    url: '/admin/blog',
    desc: '',
});
api[0].list[0].list.push({
    order: '11',
    deprecated: 'false',
    url: '/admin/blog',
    desc: '',
});
api[0].list[0].list.push({
    order: '12',
    deprecated: 'false',
    url: '/admin/blog/{id}',
    desc: '',
});
api[0].list.push({
    alias: 'BlogController',
    order: '2',
    link: '博客相关',
    desc: '博客相关',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: '/blog/search',
    desc: '搜索博客。',
});
api[0].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: '/blog/publish',
    desc: '发布博客。',
});
api[0].list[1].list.push({
    order: '3',
    deprecated: 'false',
    url: '/blog/draft',
    desc: '获取自己博客草稿列表。',
});
api[0].list[1].list.push({
    order: '4',
    deprecated: 'false',
    url: '/blog/hot',
    desc: '获取热门博客列表。',
});
api[0].list[1].list.push({
    order: '5',
    deprecated: 'false',
    url: '/blog/hot/like',
    desc: '获取点赞最多的博客列表。',
});
api[0].list[1].list.push({
    order: '6',
    deprecated: 'false',
    url: '/blog/hot/collect',
    desc: '获取收藏最多的博客列表。',
});
api[0].list[1].list.push({
    order: '7',
    deprecated: 'false',
    url: '/blog/hot/comment',
    desc: '获取评论最多的博客列表。',
});
api[0].list[1].list.push({
    order: '8',
    deprecated: 'false',
    url: '/blog/new',
    desc: '获取最新发布的博客列表。',
});
api[0].list[1].list.push({
    order: '9',
    deprecated: 'false',
    url: '/blog/follows',
    desc: '获取我关注的人的博客列表。',
});
api[0].list[1].list.push({
    order: '10',
    deprecated: 'false',
    url: '/blog/info/{blogId}',
    desc: '获取博客信息。',
});
api[0].list[1].list.push({
    order: '11',
    deprecated: 'false',
    url: '/blog/detail/{blogId}',
    desc: '获取博客详情。',
});
api[0].list[1].list.push({
    order: '12',
    deprecated: 'false',
    url: '/blog/update',
    desc: '修改博客。',
});
api[0].list[1].list.push({
    order: '13',
    deprecated: 'false',
    url: '/blog/delete/{blogId}',
    desc: '删除博客。',
});
api[0].list[1].list.push({
    order: '14',
    deprecated: 'false',
    url: '/blog/my',
    desc: '获取我发布的博客列表。',
});
api[0].list[1].list.push({
    order: '15',
    deprecated: 'false',
    url: '/blog/collect/{blogId}',
    desc: '收藏博客。',
});
api[0].list[1].list.push({
    order: '16',
    deprecated: 'false',
    url: '/blog/collect/{blogId}',
    desc: '取消收藏博客。',
});
api[0].list[1].list.push({
    order: '17',
    deprecated: 'false',
    url: '/blog/collects',
    desc: '获取我收藏的博客列表。',
});
api[0].list[1].list.push({
    order: '18',
    deprecated: 'false',
    url: '/blog/like/{blogId}',
    desc: '点赞博客。',
});
api[0].list[1].list.push({
    order: '19',
    deprecated: 'false',
    url: '/blog/like/{blogId}',
    desc: '取消点赞博客。',
});
api[0].list[1].list.push({
    order: '20',
    deprecated: 'false',
    url: '/blog/likes',
    desc: '获取当前登录用户点赞的博客列表。',
});
api[0].list[1].list.push({
    order: '21',
    deprecated: 'false',
    url: '/blog/comment',
    desc: '对指定博客进行评论。',
});
api[0].list[1].list.push({
    order: '22',
    deprecated: 'false',
    url: '/blog/comment/{commentId}',
    desc: '删除指定评论。',
});
api[0].list[1].list.push({
    order: '23',
    deprecated: 'false',
    url: '/blog/comment/{blogId}',
    desc: '获取指定博客的评论列表。',
});
api[0].list.push({
    alias: 'ChatController',
    order: '3',
    link: '私信相关',
    desc: '私信相关',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: '/user/chat',
    desc: '向指定的用户发送消息。',
});
api[0].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: '/user/chat/chats',
    desc: '获取当前登录用户的所有消息。',
});
api[0].list[2].list.push({
    order: '3',
    deprecated: 'false',
    url: '/user/chat/sessions',
    desc: '获取当前登录用户的所有会话列表。',
});
api[0].list[2].list.push({
    order: '4',
    deprecated: 'false',
    url: '/user/chat/session/{userid}',
    desc: '获取指定的会话信息。',
});
api[0].list[2].list.push({
    order: '5',
    deprecated: 'false',
    url: '/user/chat/detail/{userid}',
    desc: '获取消息信息详情（对话）',
});
api[0].list[2].list.push({
    order: '6',
    deprecated: 'false',
    url: '/user/chat/{chatid}',
    desc: '删除指定的单条消息',
});
api[0].list[2].list.push({
    order: '7',
    deprecated: 'false',
    url: '/user/chat/session/{userid}',
    desc: '删除指定的会话',
});
api[0].list.push({
    alias: 'ImageController',
    order: '4',
    link: '图片相关',
    desc: '图片相关',
    list: []
})
api[0].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: '/images/upload',
    desc: '上传图片。',
});
api[0].list[3].list.push({
    order: '2',
    deprecated: 'false',
    url: '/images/download/{id}',
    desc: '下载指定 id 的图片。',
});
api[0].list[3].list.push({
    order: '3',
    deprecated: 'false',
    url: '/images/{id}',
    desc: '获取指定 id 的图片的字节数组。',
});
api[0].list[3].list.push({
    order: '4',
    deprecated: 'false',
    url: '/images/ipsign',
    desc: '获取IP签名档图片的URL。',
});
api[0].list.push({
    alias: 'TestController',
    order: '5',
    link: '测试相关',
    desc: '测试相关',
    list: []
})
api[0].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: '/test/by0',
    desc: '返回除零错误。',
});
api[0].list[4].list.push({
    order: '2',
    deprecated: 'false',
    url: '/test/null',
    desc: '空返回测试方法。',
});
api[0].list[4].list.push({
    order: '3',
    deprecated: 'false',
    url: '/test/time',
    desc: '获取当前时间。',
});
api[0].list.push({
    alias: 'UserController',
    order: '6',
    link: '用户相关',
    desc: '用户相关',
    list: []
})
api[0].list[5].list.push({
    order: '1',
    deprecated: 'false',
    url: '/user/forget/{email}',
    desc: '发送包含用户密码的邮件到用户的邮箱，以帮助他们找回密码。',
});
api[0].list[5].list.push({
    order: '2',
    deprecated: 'false',
    url: '/user/code/{email}',
    desc: '生成并发送验证码到用户的邮箱。',
});
api[0].list[5].list.push({
    order: '3',
    deprecated: 'false',
    url: '/user/register',
    desc: '注册新用户',
});
api[0].list[5].list.push({
    order: '4',
    deprecated: 'false',
    url: '/user/login',
    desc: '用户登录。',
});
api[0].list[5].list.push({
    order: '5',
    deprecated: 'false',
    url: '/user/logout',
    desc: '用户登出。',
});
api[0].list[5].list.push({
    order: '6',
    deprecated: 'false',
    url: '/user/i',
    desc: '获取当前登录用户的信息。',
});
api[0].list[5].list.push({
    order: '7',
    deprecated: 'false',
    url: '/user/i/detail',
    desc: '获取当前登录用户的详细信息。',
});
api[0].list[5].list.push({
    order: '8',
    deprecated: 'false',
    url: '/user/update',
    desc: '修改当前登录用户的信息。',
});
api[0].list[5].list.push({
    order: '9',
    deprecated: 'false',
    url: '/user/update/email',
    desc: '修改当前登录用户的邮箱。',
});
api[0].list[5].list.push({
    order: '10',
    deprecated: 'false',
    url: '/user/update/password',
    desc: '修改当前登录用户的密码。',
});
api[0].list[5].list.push({
    order: '11',
    deprecated: 'false',
    url: '/user/info/{userid}',
    desc: '获取指定 userid 的用户信息。',
});
api[0].list[5].list.push({
    order: '12',
    deprecated: 'false',
    url: '/user/delete',
    desc: '删除当前登录用户的账户。',
});
api[0].list[5].list.push({
    order: '13',
    deprecated: 'false',
    url: '/user/follow/{userid}',
    desc: '让当前登录用户关注指定的用户。',
});
api[0].list[5].list.push({
    order: '14',
    deprecated: 'false',
    url: '/user/follow/{userid}',
    desc: '取消当前登录用户对指定用户的关注。',
});
api[0].list[5].list.push({
    order: '15',
    deprecated: 'false',
    url: '/user/followings',
    desc: '获取当前登录用户关注的用户列表。',
});
api[0].list[5].list.push({
    order: '16',
    deprecated: 'false',
    url: '/user/followers',
    desc: '获取当前登录用户的粉丝列表。',
});
api[0].list.push({
    alias: 'dict',
    order: '7',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
api[0].list[6].list.push({
    order: '1',
    deprecated: 'false',
    url: '',
    desc: '状态码字典',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value.toLocaleLowerCase();

        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    if (apiGroups.length > 0) {
        if (apiDocListSize === 1) {
            let apiData = apiGroups[0].list;
            let order = apiGroups[0].order;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+order+'_'+apiData[j].order+'_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                let doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated === 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#_'+order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+apiGroup.order+'_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#_'+apiGroup.order+'_'+ apiData[j].order + '_'+ apiData[j].link + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    let doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated === 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#_'+apiGroup.order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}