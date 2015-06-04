package org.jetbrains.anko

public fun android.gesture.GestureOverlayView.onGesturePerformed(l: (overlay: android.gesture.GestureOverlayView?, gesture: android.gesture.Gesture?) -> Unit): Unit = addOnGesturePerformedListener(l)

public fun android.support.v4.app.FragmentTabHost.onTabChanged(l: (tabId: String?) -> Unit): Unit = setOnTabChangedListener(l)

public fun android.support.v4.widget.SwipeRefreshLayout.onRefresh(l: () -> Unit): Unit = setOnRefreshListener(l)

public fun android.support.v7.widget.ActionMenuView.onMenuItemClick(l: (item: android.view.MenuItem?) -> Boolean): Unit = setOnMenuItemClickListener(l)

public fun android.support.v7.widget.SearchView.onClose(l: () -> Boolean): Unit = setOnCloseListener(l)

public fun android.support.v7.widget.SearchView.onQueryTextFocusChange(l: (v: android.view.View, hasFocus: Boolean) -> Unit): Unit = setOnQueryTextFocusChangeListener(l)

public fun android.support.v7.widget.SearchView.onSearchClick(l: (v: android.view.View) -> Unit): Unit = setOnSearchClickListener(l)

public fun android.support.v7.widget.Toolbar.onMenuItemClick(l: (item: android.view.MenuItem?) -> Boolean): Unit = setOnMenuItemClickListener(l)

public fun android.view.View.onLayoutChange(l: (v: android.view.View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) -> Unit): Unit = addOnLayoutChangeListener(l)

public fun android.view.View.onClick(l: (v: android.view.View) -> Unit): Unit = setOnClickListener(l)

public fun android.view.View.onCreateContextMenu(l: (menu: android.view.ContextMenu?, v: android.view.View?, menuInfo: android.view.ContextMenu.ContextMenuInfo?) -> Unit): Unit = setOnCreateContextMenuListener(l)

public fun android.view.View.onDrag(l: (v: android.view.View, event: android.view.DragEvent) -> Boolean): Unit = setOnDragListener(l)

public fun android.view.View.onFocusChange(l: (v: android.view.View, hasFocus: Boolean) -> Unit): Unit = setOnFocusChangeListener(l)

public fun android.view.View.onGenericMotion(l: (v: android.view.View, event: android.view.MotionEvent) -> Boolean): Unit = setOnGenericMotionListener(l)

public fun android.view.View.onHover(l: (v: android.view.View, event: android.view.MotionEvent) -> Boolean): Unit = setOnHoverListener(l)

public fun android.view.View.onKey(l: (v: android.view.View, keyCode: Int, event: android.view.KeyEvent) -> Boolean): Unit = setOnKeyListener(l)

public fun android.view.View.onLongClick(l: (v: android.view.View) -> Boolean): Unit = setOnLongClickListener(l)

public fun android.view.View.onSystemUiVisibilityChange(l: (visibility: Int) -> Unit): Unit = setOnSystemUiVisibilityChangeListener(l)

public fun android.view.View.onTouch(l: (v: android.view.View, event: android.view.MotionEvent) -> Boolean): Unit = setOnTouchListener(l)

public fun android.view.ViewStub.onInflate(l: (stub: android.view.ViewStub?, inflated: android.view.View?) -> Unit): Unit = setOnInflateListener(l)

public fun android.widget.AdapterView<out android.widget.Adapter?>.onClick(l: (v: android.view.View) -> Unit): Unit = setOnClickListener(l)

public fun android.widget.AdapterView<out android.widget.Adapter?>.onItemClick(l: (p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit): Unit = setOnItemClickListener(l)

public fun android.widget.AdapterView<out android.widget.Adapter?>.onItemLongClick(l: (p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Boolean): Unit = setOnItemLongClickListener(l)

public fun android.widget.AutoCompleteTextView.onClick(l: (v: android.view.View) -> Unit): Unit = setOnClickListener(l)

public fun android.widget.AutoCompleteTextView.onItemClick(l: (p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit): Unit = setOnItemClickListener(l)

public fun android.widget.CalendarView.onDateChange(l: (view: android.widget.CalendarView?, year: Int, month: Int, dayOfMonth: Int) -> Unit): Unit = setOnDateChangeListener(l)

public fun android.widget.Chronometer.onChronometerTick(l: (chronometer: android.widget.Chronometer?) -> Unit): Unit = setOnChronometerTickListener(l)

public fun android.widget.CompoundButton.onCheckedChange(l: (buttonView: android.widget.CompoundButton?, isChecked: Boolean) -> Unit): Unit = setOnCheckedChangeListener(l)

public fun android.widget.ExpandableListView.onChildClick(l: (parent: android.widget.ExpandableListView?, v: android.view.View?, groupPosition: Int, childPosition: Int, id: Long) -> Boolean): Unit = setOnChildClickListener(l)

public fun android.widget.ExpandableListView.onGroupClick(l: (parent: android.widget.ExpandableListView?, v: android.view.View?, groupPosition: Int, id: Long) -> Boolean): Unit = setOnGroupClickListener(l)

public fun android.widget.ExpandableListView.onGroupCollapse(l: (groupPosition: Int) -> Unit): Unit = setOnGroupCollapseListener(l)

public fun android.widget.ExpandableListView.onGroupExpand(l: (groupPosition: Int) -> Unit): Unit = setOnGroupExpandListener(l)

public fun android.widget.ExpandableListView.onItemClick(l: (p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit): Unit = setOnItemClickListener(l)

public fun android.widget.NumberPicker.onScroll(l: (view: android.widget.NumberPicker?, scrollState: Int) -> Unit): Unit = setOnScrollListener(l)

public fun android.widget.NumberPicker.onValueChanged(l: (picker: android.widget.NumberPicker, oldVal: Int, newVal: Int) -> Unit): Unit = setOnValueChangedListener(l)

public fun android.widget.RadioGroup.onCheckedChange(l: (group: android.widget.RadioGroup?, checkedId: Int) -> Unit): Unit = setOnCheckedChangeListener(l)

public fun android.widget.RatingBar.onRatingBarChange(l: (ratingBar: android.widget.RatingBar?, rating: Float, fromUser: Boolean) -> Unit): Unit = setOnRatingBarChangeListener(l)

public fun android.widget.SearchView.onClose(l: () -> Boolean): Unit = setOnCloseListener(l)

public fun android.widget.SearchView.onQueryTextFocusChange(l: (v: android.view.View, hasFocus: Boolean) -> Unit): Unit = setOnQueryTextFocusChangeListener(l)

public fun android.widget.SearchView.onSearchClick(l: (v: android.view.View) -> Unit): Unit = setOnSearchClickListener(l)

public fun android.widget.SlidingDrawer.onDrawerClose(l: () -> Unit): Unit = setOnDrawerCloseListener(l)

public fun android.widget.SlidingDrawer.onDrawerOpen(l: () -> Unit): Unit = setOnDrawerOpenListener(l)

public fun android.widget.Spinner.onItemClick(l: (p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit): Unit = setOnItemClickListener(l)

public fun android.widget.TabHost.onTabChanged(l: (tabId: String?) -> Unit): Unit = setOnTabChangedListener(l)

public fun android.widget.TextView.onEditorAction(l: (v: android.widget.TextView?, actionId: Int, event: android.view.KeyEvent?) -> Boolean): Unit = setOnEditorActionListener(l)

public fun android.widget.TimePicker.onTimeChanged(l: (view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) -> Unit): Unit = setOnTimeChangedListener(l)

public fun android.widget.VideoView.onCompletion(l: (mp: android.media.MediaPlayer?) -> Unit): Unit = setOnCompletionListener(l)

public fun android.widget.VideoView.onError(l: (mp: android.media.MediaPlayer?, what: Int, extra: Int) -> Boolean): Unit = setOnErrorListener(l)

public fun android.widget.VideoView.onPrepared(l: (mp: android.media.MediaPlayer) -> Unit): Unit = setOnPreparedListener(l)

public fun android.widget.ZoomControls.onZoomInClick(l: (v: android.view.View) -> Unit): Unit = setOnZoomInClickListener(l)

public fun android.widget.ZoomControls.onZoomOutClick(l: (v: android.view.View) -> Unit): Unit = setOnZoomOutClickListener(l)

class __GestureOverlayView_OnGestureListener : android.gesture.GestureOverlayView.OnGestureListener {
    private var _onGestureStarted: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null
    private var _onGesture: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null
    private var _onGestureEnded: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null
    private var _onGestureCancelled: ((android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null

    override fun onGestureStarted(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) = _onGestureStarted?.invoke(overlay, event)

    public fun onGestureStarted(listener: (android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) {
        _onGestureStarted = listener
    }

    override fun onGesture(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) = _onGesture?.invoke(overlay, event)

    public fun onGesture(listener: (android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) {
        _onGesture = listener
    }

    override fun onGestureEnded(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) = _onGestureEnded?.invoke(overlay, event)

    public fun onGestureEnded(listener: (android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) {
        _onGestureEnded = listener
    }

    override fun onGestureCancelled(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) = _onGestureCancelled?.invoke(overlay, event)

    public fun onGestureCancelled(listener: (android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit) {
        _onGestureCancelled = listener
    }

}

class __GestureOverlayView_OnGesturingListener : android.gesture.GestureOverlayView.OnGesturingListener {
    private var _onGesturingStarted: ((android.gesture.GestureOverlayView?) -> Unit)? = null
    private var _onGesturingEnded: ((android.gesture.GestureOverlayView?) -> Unit)? = null

    override fun onGesturingStarted(overlay: android.gesture.GestureOverlayView?) = _onGesturingStarted?.invoke(overlay)

    public fun onGesturingStarted(listener: (android.gesture.GestureOverlayView?) -> Unit) {
        _onGesturingStarted = listener
    }

    override fun onGesturingEnded(overlay: android.gesture.GestureOverlayView?) = _onGesturingEnded?.invoke(overlay)

    public fun onGesturingEnded(listener: (android.gesture.GestureOverlayView?) -> Unit) {
        _onGesturingEnded = listener
    }

}

class __ViewPager_OnPageChangeListener : android.support.v4.view.ViewPager.OnPageChangeListener {
    private var _onPageScrolled: ((Int, Float, Int) -> Unit)? = null
    private var _onPageSelected: ((Int) -> Unit)? = null
    private var _onPageScrollStateChanged: ((Int) -> Unit)? = null

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = _onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)

    public fun onPageScrolled(listener: (Int, Float, Int) -> Unit) {
        _onPageScrolled = listener
    }

    override fun onPageSelected(position: Int) = _onPageSelected?.invoke(position)

    public fun onPageSelected(listener: (Int) -> Unit) {
        _onPageSelected = listener
    }

    override fun onPageScrollStateChanged(state: Int) = _onPageScrollStateChanged?.invoke(state)

    public fun onPageScrollStateChanged(listener: (Int) -> Unit) {
        _onPageScrollStateChanged = listener
    }

}

class __SearchViewSupport_OnQueryTextListener : android.support.v7.widget.SearchView.OnQueryTextListener {
    private var _onQueryTextSubmit: ((String?) -> Boolean)? = null
    private var _onQueryTextChange: ((String?) -> Boolean)? = null

    override fun onQueryTextSubmit(query: String?) = _onQueryTextSubmit?.invoke(query) ?: false

    public fun onQueryTextSubmit(listener: (String?) -> Boolean) {
        _onQueryTextSubmit = listener
    }

    override fun onQueryTextChange(newText: String?) = _onQueryTextChange?.invoke(newText) ?: false

    public fun onQueryTextChange(listener: (String?) -> Boolean) {
        _onQueryTextChange = listener
    }

}

class __SearchViewSupport_OnSuggestionListener : android.support.v7.widget.SearchView.OnSuggestionListener {
    private var _onSuggestionSelect: ((Int) -> Boolean)? = null
    private var _onSuggestionClick: ((Int) -> Boolean)? = null

    override fun onSuggestionSelect(position: Int) = _onSuggestionSelect?.invoke(position) ?: false

    public fun onSuggestionSelect(listener: (Int) -> Boolean) {
        _onSuggestionSelect = listener
    }

    override fun onSuggestionClick(position: Int) = _onSuggestionClick?.invoke(position) ?: false

    public fun onSuggestionClick(listener: (Int) -> Boolean) {
        _onSuggestionClick = listener
    }

}

class __View_OnAttachStateChangeListener : android.view.View.OnAttachStateChangeListener {
    private var _onViewAttachedToWindow: ((android.view.View) -> Unit)? = null
    private var _onViewDetachedFromWindow: ((android.view.View) -> Unit)? = null

    override fun onViewAttachedToWindow(v: android.view.View) = _onViewAttachedToWindow?.invoke(v)

    public fun onViewAttachedToWindow(listener: (android.view.View) -> Unit) {
        _onViewAttachedToWindow = listener
    }

    override fun onViewDetachedFromWindow(v: android.view.View) = _onViewDetachedFromWindow?.invoke(v)

    public fun onViewDetachedFromWindow(listener: (android.view.View) -> Unit) {
        _onViewDetachedFromWindow = listener
    }

}

class __ViewGroup_OnHierarchyChangeListener : android.view.ViewGroup.OnHierarchyChangeListener {
    private var _onChildViewAdded: ((android.view.View?, android.view.View?) -> Unit)? = null
    private var _onChildViewRemoved: ((android.view.View?, android.view.View?) -> Unit)? = null

    override fun onChildViewAdded(parent: android.view.View?, child: android.view.View?) = _onChildViewAdded?.invoke(parent, child)

    public fun onChildViewAdded(listener: (android.view.View?, android.view.View?) -> Unit) {
        _onChildViewAdded = listener
    }

    override fun onChildViewRemoved(parent: android.view.View?, child: android.view.View?) = _onChildViewRemoved?.invoke(parent, child)

    public fun onChildViewRemoved(listener: (android.view.View?, android.view.View?) -> Unit) {
        _onChildViewRemoved = listener
    }

}

class __AbsListView_OnScrollListener : android.widget.AbsListView.OnScrollListener {
    private var _onScrollStateChanged: ((android.widget.AbsListView?, Int) -> Unit)? = null
    private var _onScroll: ((android.widget.AbsListView, Int, Int, Int) -> Unit)? = null

    override fun onScrollStateChanged(view: android.widget.AbsListView?, scrollState: Int) = _onScrollStateChanged?.invoke(view, scrollState)

    public fun onScrollStateChanged(listener: (android.widget.AbsListView?, Int) -> Unit) {
        _onScrollStateChanged = listener
    }

    override fun onScroll(view: android.widget.AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) = _onScroll?.invoke(view, firstVisibleItem, visibleItemCount, totalItemCount)

    public fun onScroll(listener: (android.widget.AbsListView, Int, Int, Int) -> Unit) {
        _onScroll = listener
    }

}

class __AdapterView_OnItemSelectedListener : android.widget.AdapterView.OnItemSelectedListener {
    private var _onItemSelected: ((android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit)? = null
    private var _onNothingSelected: ((android.widget.AdapterView<*>?) -> Unit)? = null

    override fun onItemSelected(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) = _onItemSelected?.invoke(p0, p1, p2, p3)

    public fun onItemSelected(listener: (android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit) {
        _onItemSelected = listener
    }

    override fun onNothingSelected(p0: android.widget.AdapterView<*>?) = _onNothingSelected?.invoke(p0)

    public fun onNothingSelected(listener: (android.widget.AdapterView<*>?) -> Unit) {
        _onNothingSelected = listener
    }

}

class __SearchView_OnQueryTextListener : android.widget.SearchView.OnQueryTextListener {
    private var _onQueryTextSubmit: ((String?) -> Boolean)? = null
    private var _onQueryTextChange: ((String?) -> Boolean)? = null

    override fun onQueryTextSubmit(query: String?) = _onQueryTextSubmit?.invoke(query) ?: false

    public fun onQueryTextSubmit(listener: (String?) -> Boolean) {
        _onQueryTextSubmit = listener
    }

    override fun onQueryTextChange(newText: String?) = _onQueryTextChange?.invoke(newText) ?: false

    public fun onQueryTextChange(listener: (String?) -> Boolean) {
        _onQueryTextChange = listener
    }

}

class __SearchView_OnSuggestionListener : android.widget.SearchView.OnSuggestionListener {
    private var _onSuggestionSelect: ((Int) -> Boolean)? = null
    private var _onSuggestionClick: ((Int) -> Boolean)? = null

    override fun onSuggestionSelect(position: Int) = _onSuggestionSelect?.invoke(position) ?: false

    public fun onSuggestionSelect(listener: (Int) -> Boolean) {
        _onSuggestionSelect = listener
    }

    override fun onSuggestionClick(position: Int) = _onSuggestionClick?.invoke(position) ?: false

    public fun onSuggestionClick(listener: (Int) -> Boolean) {
        _onSuggestionClick = listener
    }

}

class __SeekBar_OnSeekBarChangeListener : android.widget.SeekBar.OnSeekBarChangeListener {
    private var _onProgressChanged: ((android.widget.SeekBar, Int, Boolean) -> Unit)? = null
    private var _onStartTrackingTouch: ((android.widget.SeekBar?) -> Unit)? = null
    private var _onStopTrackingTouch: ((android.widget.SeekBar) -> Unit)? = null

    override fun onProgressChanged(seekBar: android.widget.SeekBar, progress: Int, fromUser: Boolean) = _onProgressChanged?.invoke(seekBar, progress, fromUser)

    public fun onProgressChanged(listener: (android.widget.SeekBar, Int, Boolean) -> Unit) {
        _onProgressChanged = listener
    }

    override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) = _onStartTrackingTouch?.invoke(seekBar)

    public fun onStartTrackingTouch(listener: (android.widget.SeekBar?) -> Unit) {
        _onStartTrackingTouch = listener
    }

    override fun onStopTrackingTouch(seekBar: android.widget.SeekBar) = _onStopTrackingTouch?.invoke(seekBar)

    public fun onStopTrackingTouch(listener: (android.widget.SeekBar) -> Unit) {
        _onStopTrackingTouch = listener
    }

}

class __SlidingDrawer_OnDrawerScrollListener : android.widget.SlidingDrawer.OnDrawerScrollListener {
    private var _onScrollStarted: (() -> Unit)? = null
    private var _onScrollEnded: (() -> Unit)? = null

    override fun onScrollStarted() = _onScrollStarted?.invoke()

    public fun onScrollStarted(listener: () -> Unit) {
        _onScrollStarted = listener
    }

    override fun onScrollEnded() = _onScrollEnded?.invoke()

    public fun onScrollEnded(listener: () -> Unit) {
        _onScrollEnded = listener
    }

}

class __TextWatcher : android.text.TextWatcher {
    private var _beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
    private var _onTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null
    private var _afterTextChanged: ((android.text.Editable?) -> Unit)? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = _beforeTextChanged?.invoke(s, start, count, after)

    public fun beforeTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
        _beforeTextChanged = listener
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = _onTextChanged?.invoke(s, start, before, count)

    public fun onTextChanged(listener: (CharSequence, Int, Int, Int) -> Unit) {
        _onTextChanged = listener
    }

    override fun afterTextChanged(s: android.text.Editable?) = _afterTextChanged?.invoke(s)

    public fun afterTextChanged(listener: (android.text.Editable?) -> Unit) {
        _afterTextChanged = listener
    }

}

public fun android.gesture.GestureOverlayView.onGestureListener(init: __GestureOverlayView_OnGestureListener.() -> Unit) {
    val listener = __GestureOverlayView_OnGestureListener()
    listener.init()
    addOnGestureListener(listener)
}

public fun android.gesture.GestureOverlayView.onGesturingListener(init: __GestureOverlayView_OnGesturingListener.() -> Unit) {
    val listener = __GestureOverlayView_OnGesturingListener()
    listener.init()
    addOnGesturingListener(listener)
}

public fun android.support.v4.view.ViewPager.onPageChangeListener(init: __ViewPager_OnPageChangeListener.() -> Unit) {
    val listener = __ViewPager_OnPageChangeListener()
    listener.init()
    setOnPageChangeListener(listener)
}

public fun android.support.v7.widget.SearchView.onQueryTextListener(init: __SearchViewSupport_OnQueryTextListener.() -> Unit) {
    val listener = __SearchViewSupport_OnQueryTextListener()
    listener.init()
    setOnQueryTextListener(listener)
}

public fun android.support.v7.widget.SearchView.onSuggestionListener(init: __SearchViewSupport_OnSuggestionListener.() -> Unit) {
    val listener = __SearchViewSupport_OnSuggestionListener()
    listener.init()
    setOnSuggestionListener(listener)
}

public fun android.view.View.onAttachStateChangeListener(init: __View_OnAttachStateChangeListener.() -> Unit) {
    val listener = __View_OnAttachStateChangeListener()
    listener.init()
    addOnAttachStateChangeListener(listener)
}

public fun android.view.ViewGroup.onHierarchyChangeListener(init: __ViewGroup_OnHierarchyChangeListener.() -> Unit) {
    val listener = __ViewGroup_OnHierarchyChangeListener()
    listener.init()
    setOnHierarchyChangeListener(listener)
}

public fun android.widget.AbsListView.onScrollListener(init: __AbsListView_OnScrollListener.() -> Unit) {
    val listener = __AbsListView_OnScrollListener()
    listener.init()
    setOnScrollListener(listener)
}

public fun android.widget.AdapterView<out android.widget.Adapter?>.onItemSelectedListener(init: __AdapterView_OnItemSelectedListener.() -> Unit) {
    val listener = __AdapterView_OnItemSelectedListener()
    listener.init()
    setOnItemSelectedListener(listener)
}

public fun android.widget.AutoCompleteTextView.onItemSelectedListener(init: __AdapterView_OnItemSelectedListener.() -> Unit) {
    val listener = __AdapterView_OnItemSelectedListener()
    listener.init()
    setOnItemSelectedListener(listener)
}

public fun android.widget.RadioGroup.onHierarchyChangeListener(init: __ViewGroup_OnHierarchyChangeListener.() -> Unit) {
    val listener = __ViewGroup_OnHierarchyChangeListener()
    listener.init()
    setOnHierarchyChangeListener(listener)
}

public fun android.widget.SearchView.onQueryTextListener(init: __SearchView_OnQueryTextListener.() -> Unit) {
    val listener = __SearchView_OnQueryTextListener()
    listener.init()
    setOnQueryTextListener(listener)
}

public fun android.widget.SearchView.onSuggestionListener(init: __SearchView_OnSuggestionListener.() -> Unit) {
    val listener = __SearchView_OnSuggestionListener()
    listener.init()
    setOnSuggestionListener(listener)
}

public fun android.widget.SeekBar.onSeekBarChangeListener(init: __SeekBar_OnSeekBarChangeListener.() -> Unit) {
    val listener = __SeekBar_OnSeekBarChangeListener()
    listener.init()
    setOnSeekBarChangeListener(listener)
}

public fun android.widget.SlidingDrawer.onDrawerScrollListener(init: __SlidingDrawer_OnDrawerScrollListener.() -> Unit) {
    val listener = __SlidingDrawer_OnDrawerScrollListener()
    listener.init()
    setOnDrawerScrollListener(listener)
}

public fun android.widget.TableLayout.onHierarchyChangeListener(init: __ViewGroup_OnHierarchyChangeListener.() -> Unit) {
    val listener = __ViewGroup_OnHierarchyChangeListener()
    listener.init()
    setOnHierarchyChangeListener(listener)
}

public fun android.widget.TableRow.onHierarchyChangeListener(init: __ViewGroup_OnHierarchyChangeListener.() -> Unit) {
    val listener = __ViewGroup_OnHierarchyChangeListener()
    listener.init()
    setOnHierarchyChangeListener(listener)
}

public fun android.widget.TextView.textChangedListener(init: __TextWatcher.() -> Unit) {
    val listener = __TextWatcher()
    listener.init()
    addTextChangedListener(listener)
}

