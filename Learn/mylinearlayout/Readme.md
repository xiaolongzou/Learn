#LinearLayout
属性：
    orientation: 排列方式
    gravity：控制组件所包含的子元素的对齐方式。可多个组合比如center|bottom
    layout_gravity: 控制改组件在父容器的对齐方式
    background：背景图片/颜色
    divider：分割线，showDividers才显示
    showDividers：设置分割线所在的位置，none，beginning，middle，end
    dividerPadding：设置分割线的padding
    layout_weight: 权重，用来等比划划分区域的 本质上是将剩余空间进行分配
    layout_weight 栗子：
        3个子元素
        layout_height为match_parent
        layout_weight为2 1 1
        剩余空间 1 - 3 = -2
        1 - 2 * 2/4 = 0
        1 - 2 * 1/4 = 1/2
        1 - 2 * 1/4 = 1/2
        实际使用中通过layout_weight去等比划分 一般将layout_height设置为0（orientation：vertical）
